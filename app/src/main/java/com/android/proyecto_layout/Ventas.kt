package com.android.proyecto_layout
import java.io.FileDescriptor

data class Ventas (
    var id: String? = null,
    var descripcion: String? = null,
    var nombre: String? = null,
    var cantidad: Int? = null,
    var pagounidad: Int? = null,
    var locationX: String = "",
    var locationY: String = "",
    var resuelto: Boolean? = null,
    var userid: String = ""
)