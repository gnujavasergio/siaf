CREATE TABLE usuarios(
    idUsuario SERIAL NOT NULL,
    loginUsuario VARCHAR(100),
    passwordUsuario VARCHAR(200),
    nombresUsuario VARCHAR(200),
    descripcionUsuario TEXT,
    tipoUsuario VARCHAR(13),
    PRIMARY KEY(idUsuario)
);

CREATE TABLE entidades(
    idEntidad SERIAL NOT NULL,
    nombreEntidad VARCHAR(200),
    siglaEntidad VARCHAR(150),
    institucionEntidad VARCHAR(200),
    estadoEntidad BOOLEAN,
    PRIMARY KEY(idEntidad)
);

CREATE TABLE unidades(
    idUnidad SERIAL,
    descripcionUnidad VARCHAR(100),
    ciudadUnidad VARCHAR(50),
    estadoUnidad BOOLEAN DEFAULT FALSE,
    idEntidad INTEGER,
    codigoUnidad INTEGER,
    PRIMARY KEY(idUnidad),
    FOREIGN KEY (idEntidad) REFERENCES entidades(idEntidad)
);

CREATE TABLE oficinas(    
    idOficina SERIAL NOT NULL,
    nombreOficina VARCHAR(100),
    observacionOficina TEXT,
    fechaRegistroOficina TIMESTAMP,
    fechaModificacionOficina TIMESTAMP,
    idUsuario INTEGER,
    idUnidad INTEGER,
    codigoOficina INTEGER,
    PRIMARY KEY(idOficina),
    FOREIGN KEY(idUsuario) REFERENCES usuarios(idUsuario),
    FOREIGN KEY(idUnidad) REFERENCES unidades(idUnidad)
);

CREATE TABLE responsables (    
    idResponsable SERIAL ,  
    nombreResponsable VARCHAR(30),
    cargoResponsable VARCHAR(100),
    ciResponsable VARCHAR(10),            
    fechaRegistroResponsable TIMESTAMP,
    fechaModificacionResponsable TIMESTAMP,
    idUsuario INTEGER,
    idOficina INTEGER,    
    codigoResponsable INTEGER,
    PRIMARY KEY(idResponsable),
    FOREIGN KEY(idUsuario) REFERENCES usuarios(idUsuario),
    FOREIGN KEY(idOficina) REFERENCES oficinas(idOficina)
);

CREATE TABLE grupos(
    idGrupo SERIAL,
    nombreGrupo VARCHAR(100),
    observacionGrupo TEXT,
    vidaUtilGrupo INTEGER,
    depreciarGrupo BOOLEAN,
    actualizarGrupo BOOLEAN,
    fechaRegistroGrupo TIMESTAMP,
    fechaModificacionGrupo TIMESTAMP,
    idUsuario INTEGER,       
    PRIMARY KEY(idGrupo),
    FOREIGN KEY(idUsuario) REFERENCES usuarios(idUsuario)    
);

CREATE TABLE auxiliares(
    idAuxiliar SERIAL,
    nombreAuxIliar VARCHAR(100),       
    fechaRegistroAuxiliar TIMESTAMP,
    fechaModificacionAuxiliar TIMESTAMP,
    idUsuario INTEGER,   
    idGrupo INTEGER,
    idUnidad INTEGER,
    codigoAuxiliar INTEGER,
    PRIMARY KEY(idAuxiliar),
    FOREIGN KEY(idUsuario) REFERENCES usuarios(idUsuario),    
    FOREIGN KEY(idGrupo) REFERENCES grupos(idGrupo),
    FOREIGN KEY(idUnidad) REFERENCES unidades(idUnidad)
);

CREATE TABLE activosFijos(    
    idActivoFijo VARCHAR(50),    
    codigoADI VARCHAR(100),
    incorporacion DATE,
    indiceUFV VARCHAR(10),
    descripcion TEXT,
    apiEstado VARCHAR(9),    
    estado INTEGER,
    observaciones TEXT,
    rube VARCHAR(50),
    financiador VARCHAR(100),
    numeroConvenio INTEGER,
    costoInicial FLOAT,  
    depreciacionAcumuladaInicial FLOAT,
    factorActual FLOAT,
    valor FLOAT,
    porcentajeDepreciacion FLOAT,
    depreciacionGestion FLOAT,
    depreciacionAcumulada FLOAT,  
    valorNeto FLOAT,
    dias INTEGER,   
    vidaUtil INTEGER,
    gestion INTEGER,
    fechaModificacionActivoFijo TIMESTAMP,
    revaluo BOOLEAN DEFAULT FALSE,    
    cerrarGestion BOOLEAN DEFAULT FALSE,
    baja BOOLEAN DEFAULT FALSE,   
    transfer BOOLEAN DEFAULT FALSE,
    idAuxiliar INTEGER,
    idResponsable INTEGER,
    idUsuario INTEGER,
    PRIMARY KEY (idActivoFijo),
    FOREIGN KEY(idAuxiliar) REFERENCES auxiliares(idAuxiliar),
    FOREIGN KEY(idResponsable) REFERENCES responsables(idResponsable),
    FOREIGN KEY(idUsuario) REFERENCES usuarios(idUsuario)   
);

CREATE TABLE revaluos(
    idRevaluo SERIAL,
    idActivoFijo VARCHAR(50),
    incorporacionRevaluo DATE,
    fechaRevaluoTecnicoRevaluo DATE,
    costoInicialRevaluo FLOAT,
    costoInicialNuevoRevaluo FLOAT,
    vidaUtilRevaluo INTEGER,
    vidaUtilNuevoRevaluo INTEGER,
    indiceUFVRevaluo VARCHAR(10),
    indiceUFVNuevoRevaluo VARCHAR(10),
    disposicionRespaldoRevaluo VARCHAR(50),
    motivoRevaluo TEXT,
    depreciacionGestionRevaluo FLOAT,
    depreciacionAcumuladaRevaluo FLOAT, 
    valorNetoRevaluo FLOAT,  
    gestionRevaluo INTEGER,    
    idUsuario INTEGER,
    PRIMARY KEY(idRevaluo),    
    FOREIGN KEY(idUsuario) REFERENCES usuarios(idUsuario),
    FOREIGN KEY (idActivoFijo) REFERENCES activosFijos(idActivoFijo)
);

CREATE TABLE bajas(
    idBaja SERIAL,
    idActivoFijo VARCHAR(50),
    fechaBaja DATE,
    disposicionRespaldo VARCHAR(50),
    motivoBaja VARCHAR(300),    
    motivo TEXT,
    idUnidad INTEGER,
    idUsuario INTEGER,
    PRIMARY KEY(idBaja),
    FOREIGN KEY(idUnidad) REFERENCES unidades(idUnidad),
    FOREIGN KEY(idUsuario) REFERENCES usuarios(idUsuario),
    FOREIGN KEY (idActivoFijo) REFERENCES activosFijos(idActivoFijo)
);

CREATE TABLE configuraciones (
    idConfiguracion SERIAL,
    UFV VARCHAR(10),
    fechaUFV DATE,    
    PRIMARY KEY (idConfiguracion)
);
