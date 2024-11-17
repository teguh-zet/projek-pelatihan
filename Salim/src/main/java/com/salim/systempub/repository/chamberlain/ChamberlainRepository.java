package com.salim.systempub.repository.chamberlain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.chamberlain.Chamberlain;

@Repository
public interface ChamberlainRepository extends JpaRepository<Chamberlain,Long> {

}
