package com.kgodlewski.myapplication.repository;


import com.kgodlewski.myapplication.domain.Messages;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends CassandraRepository<Messages, Integer> {

    @Query("Delete from messages USING TIMESTAMP :timeStamp WHERE id = 1")
    void deleteMessageTimeStampBeforeAndById(@Param("timeStamp") long timeStamp);

    @Query("Delete from messages WHERE id = 1 and second_id = :second_id IF magic_number = :magic_number")
    Optional<Messages> deleteMessageByMagicNumber(@Param("second_id") UUID second_id, @Param("magic_number") Integer magic_number);

    @Query("Select * from messages WHERE id = 1 AND email = :email ALLOW FILTERING ")
    List<Messages> findByEmail(@Param("email") String email);

    @Query("Select * from messages WHERE id = 1 AND email = :email LIMIT :limit ALLOW FILTERING")
    List<Messages> findByEmailWithLimit(@Param("email") String email, @Param("limit") Integer limit);

    @Query("Select * from messages WHERE id = 1 AND magic_number = :magic_number ALLOW FILTERING")
    List<Messages> findByMagicNumber(@Param("magic_number") Integer magic_number);

}
