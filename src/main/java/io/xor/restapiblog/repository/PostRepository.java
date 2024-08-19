package io.xor.restapiblog.repository;

import io.xor.restapiblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}
