SELECT
     activosfijos.idactivofijo AS activosfijos_idactivofijo,
     activosfijos.incorporacion AS activosfijos_incorporacion,
     activosfijos.costoinicial AS activosfijos_costoinicial,
     activosfijos.depreciacionacumulada AS activosfijos_depreciacionacumulada,
     activosfijos.valorneto AS activosfijos_valorneto,
     grupos.nombregrupo AS grupos_nombregrupo,
     auxiliares.nombreauxiliar AS auxiliares_nombreauxiliar,
     activosfijos.descripcion AS activosfijos_descripcion,
     activosfijos.indiceufv AS activosfijos_indiceufv,
     activosfijos.vidautil AS activosfijos_vidautil,
     activosfijos.porcentajedepreciacion AS activosfijos_porcentajedepreciacion,
     activosfijos.dias AS activosfijos_dias,
     activosfijos.factoractual AS activosfijos_factoractual,
     responsables.nombreresponsable AS responsables_nombreresponsable,
     oficinas.nombreoficina AS oficinas_nombreoficina,
     activosfijos.valor AS activosfijos_valor,
     responsables.idresponsable AS responsables_idresponsable,
     oficinas.idoficina AS oficinas_idoficina,
     auxiliares.idauxiliar AS auxiliares_idauxiliar,
     grupos.idgrupo AS grupos_idgrupo
FROM
     grupos grupos INNER JOIN auxiliares auxiliares ON grupos.idgrupo = auxiliares.idgrupo
     INNER JOIN activosfijos activosfijos ON auxiliares.idauxiliar = activosfijos.idauxiliar
     INNER JOIN responsables responsables ON activosfijos.idresponsable = responsables.idresponsable
     INNER JOIN oficinas oficinas ON responsables.idoficina = oficinas.idoficina
WHERE 
$P!{p_condicion}