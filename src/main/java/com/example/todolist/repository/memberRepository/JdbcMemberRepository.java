package com.example.todolist.repository.memberRepository;

import com.example.todolist.domain.member.Gender;
import com.example.todolist.domain.member.Member;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemberRepository implements MemberRepository {
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
        String sql = "select * from member";
        return template.query(sql, membersRowMapper());
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        String sql = "select * from member where memberId = ?";
        return template.queryForObject(sql, memberRowMapper(), memberId);
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        String sql = "select * from member where username = ?";
        try {
            return template.queryForObject(sql, memberRowMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Long memberId) {
        String sql = "delete from member where memberId = ?";
        template.update(sql, memberId);
    }

    @Override
    public Member updateById(Long memberId, Member member) {
        String sql = "update member set username = ?, password = ?, name = ?, gender = ? where memberId = ?";
        template.update(sql, member.getUsername(), member.getPassword(), member.getName(), member.getGender().name(), memberId);
        return member;
    }

    private RowMapper<Member> membersRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberId(rs.getLong("memberId"));
            member.setUsername(rs.getString("username"));
            member.setName(rs.getString("name"));
            member.setPassword(rs.getString("password"));
            member.setGender(Gender.valueOf(rs.getString("gender")));
            member.setEmail(rs.getString("email"));
            return member;
        };
    }

    private RowMapper<Optional<Member>> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberId(rs.getLong("memberId"));
            member.setUsername(rs.getString("username"));
            member.setName(rs.getString("name"));
            member.setPassword(rs.getString("password"));
            member.setGender(Gender.valueOf(rs.getString("gender")));
            member.setEmail(rs.getString("email"));
            return Optional.of(member);
        };
    }
}
