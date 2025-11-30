package com.informatorio.moodtrack.moodtrack.service.habito.impl;

import com.informatorio.moodtrack.moodtrack.dto.habito.HabitoCreateDto;
import com.informatorio.moodtrack.moodtrack.dto.habito.HabitoDto;
import com.informatorio.moodtrack.moodtrack.mapper.habito.HabitoMapper;
import com.informatorio.moodtrack.moodtrack.model.Habito;
import com.informatorio.moodtrack.moodtrack.repository.habito.HabitoRepository;
import com.informatorio.moodtrack.moodtrack.service.habito.HabitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitoServiceImpl implements HabitoService {

    private final HabitoRepository habitoRepository;

    @Autowired
    public HabitoServiceImpl(HabitoRepository habitoRepository) {
        this.habitoRepository = habitoRepository;
    }

    @Override
    public List<HabitoDto> getAllHabitos() {
        return HabitoMapper.toDtoList(habitoRepository.findAll());
    }

    @Override
    public HabitoDto createHabito(HabitoCreateDto habitoCreateDto) {
            Habito habito = HabitoMapper.toEntity(habitoCreateDto);
            habito = habitoRepository.save(habito);
            return HabitoMapper.toDto(habito);
    }
}
