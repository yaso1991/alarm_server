package club.yaso91.alarm_server.mapper;

import club.yaso91.alarm_server.entity.CardReader;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CardReaderMapper {
    ArrayList<CardReader> selectAll();
}
