SELECT
     activosfijos.idactivofijo AS activosfijos_idactivofijo,
     activosfijos.valorneto AS activosfijos_valorneto,
     grupos.nombregrupo AS grupos_nombregrupo,
     auxiliares.nombreauxiliar AS auxiliares_nombreauxiliar,
     responsables.nombreresponsable AS responsables_nombreresponsable,
     oficinas.nombreoficina AS oficinas_nombreoficina,
     activosfijos.valor AS activosfijos_valor,
     responsables.idresponsable AS responsables_idresponsable,
     oficinas.idoficina AS oficinas_idoficina,
     auxiliares.idauxiliar AS auxiliares_idauxiliar,
     grupos.idgrupo AS grupos_idgrupo,
     activosfijos.descripcion AS activosfijos_descripcion,
     activosfijos.rube AS activosfijos_rube,
     activosfijos.financiador AS activosfijos_financiador,
     activosfijos.numeroconvenio AS activosfijos_numeroconvenio,
     activosfijos.apiestado AS activosfijos_apiestado
FROM
     grupos grupos INNER JOIN auxiliares auxiliares ON grupos.idgrupo = auxiliares.idgrupo
     INNER JOIN activosfijos activosfijos ON auxiliares.idauxiliar = activosfijos.idauxiliar
     INNER JOIN responsables responsables ON activosfijos.idresponsable = responsables.idresponsable
     INNER JOIN oficinas oficinas ON responsables.idoficina = oficinas.idoficina
WHERE
activosFijos.apiEstado = 'APROBADO' AND $P!{p_condicion}

ORDER BY 

activosFijos.financiador
