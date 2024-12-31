package com.proyectofinal.forohubbackend.Domain.Tema;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TemaRepository extends JpaRepository<Tema, Long> {
   @Query( "SELECT t FROM Tema t WHERE t.activo=true ")
   Page<Tema> findByActivoTrue(Pageable pageable);
}
