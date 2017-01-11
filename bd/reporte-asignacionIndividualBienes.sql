
SELECT
     activosfijos.idactivofijo AS activosfijos_idactivofijo,
     grupos.nombregrupo AS grupos_nombregrupo,
     auxiliares.nombreauxiliar AS auxiliares_nombreauxiliar,
     oficinas.nombreoficina AS oficinas_nombreoficina,
     responsables.nombreresponsable AS responsables_nombreresponsable,
     activosfijos.descripcion AS activosfijos_descripcion,
     auxiliares.idauxiliar AS auxiliares_idauxiliar,
     grupos.idgrupo AS grupos_idgrupo,
     responsables.idresponsable AS responsables_idresponsable,
     oficinas.idoficina AS oficinas_idoficina,
     activosfijos.estado AS activosfijos_estado
FROM
     grupos grupos INNER JOIN auxiliares auxiliares ON grupos.idgrupo = auxiliares.idgrupo
     INNER JOIN activosfijos activosfijos ON auxiliares.idauxiliar = activosfijos.idauxiliar
     INNER JOIN responsables responsables ON activosfijos.idresponsable = responsables.idresponsable
     INNER JOIN oficinas oficinas ON responsables.idoficina = oficinas.idoficina
WHERE
$P!{p_condicion}
ORDER BY
     activosfijos.idActivoFijo ASC