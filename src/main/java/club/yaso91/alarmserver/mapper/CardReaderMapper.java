package club.yaso91.alarmserver.mapper;

import club.yaso91.alarmserver.entity.CardReader;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CardReaderMapper {
    ArrayList<CardReader> selectAll();
}
