package br.com.skillswap.profileservice.repository;

import br.com.skillswap.profileservice.dto.ProfileWithAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProfileWithAddressRepositoryImpl implements ProfileWithAddressRepositoryCustom {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProfileWithAddressDTO> findAllProfiles() {
        String sql = "SELECT * FROM profile_with_address"; // Ajuste o SQL conforme necessário
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProfileWithAddressDTO dto = new ProfileWithAddressDTO();
            dto.setProfileId(rs.getLong("profile_id"));
            dto.setFirstName(rs.getString("first_name"));
            dto.setLastName(rs.getString("last_name"));
            dto.setBio(rs.getString("bio"));
            dto.setPictureUrl(rs.getString("picture_url"));
            dto.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
            dto.setUserId(rs.getLong("user_id"));
            dto.setAddressId(rs.getLong("address_id"));
            dto.setStreet(rs.getString("street"));
            dto.setNumber(rs.getString("number"));
            dto.setZipCode(rs.getString("zip_code"));
            dto.setState(rs.getString("state"));
            dto.setCountry(rs.getString("country"));
            return dto;
        });
    }
}

