ALTER TABLE temas
ADD COLUMN usuario_id bigint not null,
ADD CONSTRAINT fk_usuario
FOREIGN KEY(usuario_id) REFERENCES usuarios(id);