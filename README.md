README DE EL EXAMEN
EL proyecto busca implementar un sistema de archivos que permitira:
-Procesar archivos individuales o carpetas
-Aplicar validaciones, transformaciones y almacenamiento
-Notificar segun el tipo de archivo
-Generar resultados de procesamiento 
La intencion es demostrar que se usar patrones de diseño y buenas practicas

estoy usando el patron Strategy para el comportamiento de cada tipo de archivo
Invoice 
Contract
Report
esto ayudara a poner nuevos tipos de documentos sin modificar el codigo existente 

tambien decidi usar el Template Method para que que hereden una clase abstracta y defina el comportamiento 

Vaidar->transformar->guardar->notificar

tambien la cadena de responsablidad para las validaciones para poder agregar nuevas validaciones  facilmente en el futuro

a su vez igual el composite pattern para procesar archivos y carpetas de manera uniforme 
 InMemoryFileRepository sera para almacenar en memoria los datos
 para las notificaciones igual usamos strategy 