package com.example.todolist.repository;

import com.example.todolist.domain.Memo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemoRepository implements MemoRepository{

    private final JdbcTemplate template;

    public JdbcMemoRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Memo memoSave(Memo memo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into memo (memo, created, dTime) values (?, ?, ?)";
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"memoId"});
            ps.setString(1, memo.getMemo());
            ps.setTimestamp(2, Timestamp.valueOf(memo.getCreated()));
            ps.setTimestamp(3, Timestamp.valueOf(memo.getDTime()));
            return ps;
        }, keyHolder
    );
        Long generatedId = keyHolder.getKey().longValue();
        memo.setMemoId(generatedId);
        return memo;
    }

    @Override
    public List<Memo> findMemoAll() {
        String sql = "select * from memo";
        return template.query(sql, memosRowMapper());
    }

    private RowMapper<Memo> memosRowMapper() {
        return (rs, rowNum) -> {
            Memo memo = new Memo();
            memo.setMemoId(rs.getLong("memoId"));
            memo.setMemo(rs.getString("memo"));
            memo.setCreated(rs.getTimestamp("created").toLocalDateTime());
            memo.setDTime(rs.getTimestamp("dTime").toLocalDateTime());
            return memo;
        };
    }

    @Override
    public Optional<Memo> findById(Long memoId) {
        String sql = "select * from memo where memoId = ?";
        return template.queryForObject(sql, memoRowMapper(), memoId);
    }

    @Override
    public void deleteById(Long memoId) {
        String sql = "delete from memo where memoId = ?";
        template.update(sql, memoId);
    }

    @Override
    public Memo updateById(Long memoId, Memo memo) {
        String sql = "update memo set memo = ?, created = ?, dTime = ? where memoId = ?";
        template.update(sql, memo.getMemo(),LocalDateTime.now(), memo.getDTime(), memoId);
        return memo;
    }

    private RowMapper<Optional<Memo>> memoRowMapper() {
        return (rs, rowNum) -> {
            Memo memo = new Memo();
            memo.setMemoId(rs.getLong("memoId"));
            memo.setMemo(rs.getString("memo"));
            memo.setCreated(rs.getTimestamp("created").toLocalDateTime());
            memo.setDTime(rs.getTimestamp("dTime").toLocalDateTime());
            return Optional.of(memo);
        };
    }
}
