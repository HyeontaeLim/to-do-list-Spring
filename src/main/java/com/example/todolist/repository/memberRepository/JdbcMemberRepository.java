package com.example.todolist.repository.memberRepository;

import com.example.todolist.controller.memberController.dto.AddUpdateMemberForm;
import com.example.todolist.domain.member.Member;
import com.example.todolist.domain.memo.Memo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemberRepository implements MemberRepository{
    private final JdbcTemplate template;

    public JdbcMemberRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member memberSave(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into member (username, password, name, gender, email) values (?, ?, ?, ?, ?)";
        template.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[]{"memberId"});
                    ps.setString(1, member.getUsername());
                    ps.setString(2, member.getPassword());
                    ps.setString(3, member.getName());
                    ps.setString(4, member.getGender().name());
                    ps.setString(5, member.getEmail());
                    return ps;
                }, keyHolder
        );
        Long generatedId = keyHolder.getKey().longValue();
        member.setMemberId(generatedId);

        return member;
    }

    @Override
    public List<Member> findMemberAll() {
        return List.of();
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long memberId) {

    }

    @Override
    public Member updateById(Long memberId, Member member) {
        return null;
    }
}
