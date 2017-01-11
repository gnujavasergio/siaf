SELECT
     activosfijos.idactivofijo AS activosfijos_idactivofijo,
     activosfijos.incorporacion AS activosfijos_incorporacion,
     activosfijos.costoinicial AS activosfijos_costoinicial,
     activosfijos.depreciacionacumulada AS activosfijos_depreciacionacumulada,
     activosfijos.valorneto AS activosfijos_valorneto,
     grupos.nombregrupo AS grupos_nombregrupo,
     auxiliares.nombreauxiliar AS auxiliares_nombreauxiliar,
     oficinas.nombreoficina AS oficinas_nombreoficina,
     responsables.nombreresponsable AS responsables_nombreresponsable,
     activosfijos.descripcion AS activosfijos_descripcion,
     activosfijos.valor AS activosfijos_valor,
     activosfijos.depreciaciongestion AS activosfijos_depreciaciongestion,
     responsables.idresponsable AS responsables_idresponsable,
     auxiliares.idauxiliar AS auxiliares_idauxiliar,
     oficinas.idoficina AS oficinas_idoficina,
     grupos.idgrupo AS grupos_idgrupo
FROM
     grupos grupos INNER JOIN auxiliares auxiliares ON grupos.idgrupo = auxiliares.idgrupo
     INNER JOIN activosfijos activosfijos ON auxiliares.idauxiliar = activosfijos.idauxiliar
     INNER JOIN responsables responsables ON activosfijos.idresponsable = responsables.idresponsable
     INNER JOIN oficinas oficinas ON responsables.idoficina = oficinas.idoficina
WHERE 
$P!{p_condicion}

ORDER BY
     activosfijos.idActivoFijo ASC