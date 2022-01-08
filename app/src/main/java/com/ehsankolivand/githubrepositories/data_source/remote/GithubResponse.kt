package com.ehsankolivand.githubrepositories.data_source.remote

import retrofit2.Response
import java.util.regex.Pattern

sealed class GithubResponse<T> {

    companion object{
        fun<T> create(error: Throwable): ApiErrorResponse<T>{
            return ApiErrorResponse(error.message ?: "unknown error")
        }

        fun<T> create(response: Response<T>): GithubResponse<T>{
            return if(response.isSuccessful){
                val body = response.body()
                if(body == null || response.code() == 204){
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        body = body,
                        linksHeaders = response.headers()?.get("link")
                    )
                }
            }else{
                val msg = response.errorBody()?.string()
                val errorMsg = if(msg.isNullOrEmpty()){
                    response.message()
                }else{
                    msg
                }
                ApiErrorResponse(errorMsg ?: "unknown error")
            }
        }

    }
}

class ApiEmptyResponse<T>: GithubResponse<T>()

data class ApiSuccessResponse<T>(
    val body: T,
    val links: Map<String, String>
): GithubResponse<T>(){
    constructor(body: T, linksHeaders: String?): this(
        body = body,
        links = linksHeaders?.extractLinks()?: emptyMap()//Links de la paginacion(next y prev)
    )

    val nextPage: Int? by lazy ( LazyThreadSafetyMode.NONE ){//devuelve el numero de la pag sig
        links[NEXT_LINK]?.let{
                next->
            val matcher = PAGE_PATTERN.matcher(next)
            if(!matcher.find() || matcher.groupCount() != 1){
                null
            }else{
                try {
                    Integer.parseInt(matcher.group(1))
                }catch (ex: NumberFormatException){
                    null
                }
            }
        }
    }

    companion object{
        //https://api.github.com/search/repositories?q=tetris&page=1>; rel="prev", <https://api.github.com/search/repositories?q=tetris&page=3>; rel="next"
        private val LINK_PATTERN = Pattern.compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")//patron para identificar a esta parte(https://api.github.com/search/repositories?q=tetris)
        private val PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)")//patron para identificar a esta parte(page=1)
        private const val NEXT_LINK = "next"

        private fun String.extractLinks(): Map<String, String>{//Extrae los links
            val links = mutableMapOf<String, String>()
            val matcher = LINK_PATTERN.matcher(this)//se aloja los links que se encuentren en links header y q coincidan con LINK_PATTERN

            while (matcher.find()){//el metodo find de matchet intenta encontrar la sig, secuencia q cumple el patron y devuelve un boolean si la ha encontrado
                val count = matcher.groupCount()//matcher.groupCount() devuelve el numero de veces q ha encontrado el patron
                if(count == 2){
                    links[matcher.group(2)] = matcher.group(1)//ej: links["next"] = https://api.github.com/search/repositories?q=tetris&page=3
                }
            }
            return links
        }
    }
}
data class ApiErrorResponse<T>(
    val errorMessage: String
): GithubResponse<T>()