package com.navi.nbcampjavaspringcrudexample.database.repository;

import com.navi.nbcampjavaspringcrudexample.database.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
