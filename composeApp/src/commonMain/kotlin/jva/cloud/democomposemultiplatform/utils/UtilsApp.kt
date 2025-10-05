package jva.cloud.democomposemultiplatform.utils

object UtilsApp {
    const val DEFAULT_IMAGE =
        "https://okdiario.com/img/2020/02/03/-que-hora-es-en-el-espacio-esta-es-la-respuesta.jpg"

    fun reprocessImageFromApi(images: List<String>): String {
        var finalImageStr: String = DEFAULT_IMAGE
        images.firstOrNull()?.let { image ->
            finalImageStr = image.replace(oldValue = "[\"", newValue = "")
                .replace(oldValue = "\"]", newValue = "")
                .replace(oldValue = "\"", newValue = "")
        }
        return finalImageStr
    }
}