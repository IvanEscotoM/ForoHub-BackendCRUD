CREATE TABLE respuestas
(
    id         BIGINT        NOT NULL AUTO_INCREMENT,
    mensaje    VARCHAR(1500) NOT NULL,
    fecha      TIMESTAMP     NOT NULL,
    tema_id    BIGINT        NOT NULL,
    usuario_id BIGINT        NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_tema FOREIGN KEY (tema_id) REFERENCES temas (id),
    CONSTRAINT fk_usuario_respuesta FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
)