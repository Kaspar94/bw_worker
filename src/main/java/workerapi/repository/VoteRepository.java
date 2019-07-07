package workerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import workerapi.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long>{}
