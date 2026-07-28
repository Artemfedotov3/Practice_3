package com.spring.springboot.service.service;

import com.spring.springboot.api.dto.ExoticBeastRequestDto;
import com.spring.springboot.api.dto.ExoticBeastResponseDto;
import com.spring.springboot.service.entity.ExoticBeastEntity;
import com.spring.springboot.service.entity.UnitEntity;
import com.spring.springboot.service.mapper.ExoticBeastMapper;
import com.spring.springboot.service.rabbit.ExoticBeastEventPublisher;
import com.spring.springboot.service.repository.ExoticBeastRepository;
import com.spring.springboot.service.repository.UnitRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Tag("exoticBeast")
@Tag("service")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.DisplayName.class)
@ExtendWith(MockitoExtension.class)
public class ExoticBeastServiceImplTest {

    private static final ExoticBeastRequestDto TEST_EXOTIC_BEAST_REQUEST = new ExoticBeastRequestDto();

    @Mock
    private ExoticBeastRepository exoticBeastRepository;

    @Mock
    private ExoticBeastMapper exoticBeastMapper;

    @Mock
    private ExoticBeastEventPublisher exoticBeastEventPublisher;

    @Mock
    private UnitRepository unitRepository;

    @InjectMocks
    private ExoticBeastServiceImpl exoticBeastServiceImpl;

    @BeforeAll
    static void init(){
        System.out.println("Before all ");
        TEST_EXOTIC_BEAST_REQUEST.setName("Dustback Helamite");
        TEST_EXOTIC_BEAST_REQUEST.setDescription("даёт спец. правило (верхом)");
        TEST_EXOTIC_BEAST_REQUEST.setMove(8);
        TEST_EXOTIC_BEAST_REQUEST.setCost(60);
        TEST_EXOTIC_BEAST_REQUEST.setUnitId(1L);
    }

    @BeforeEach
    void prepare(){
        System.out.println("Before each ");
    }

    @Test
    void exoticBeastsEmptyIfNoExoticBeastAdded(){

        System.out.println("Test exoticBeastsEmptyIfNoExoticBeastAdded " + this);
        var exoticBeasts = exoticBeastServiceImpl.getAllExoticBeasts();
        Assertions.assertTrue(exoticBeasts.isEmpty(), () -> "Exotic beast list should be empty");
    }

    @Test
    @DisplayName("Create exotic beast test")
    void createExoticBeastTest(){

        UnitEntity unitEntity = new UnitEntity();
        unitEntity.setId(1L);
        unitEntity.setName("Intercessor");

        ExoticBeastEntity entity = ExoticBeastEntity.builder()
                .id(1L)
                .name("Dustback Helamite")
                .build();

        ExoticBeastRequestDto request = ExoticBeastRequestDto.builder()
                .name("Dustback Helamite")
                .unitId(1L)
                .build();

        ExoticBeastResponseDto response = ExoticBeastResponseDto.builder()
                .id(1L)
                .name("Dustback Helamite")
                .build();

        when(unitRepository.findById(1L)).thenReturn(Optional.of(unitEntity));
        when(exoticBeastRepository.save(any(ExoticBeastEntity.class))).thenReturn(entity);
        when(exoticBeastMapper.toDto(any(ExoticBeastEntity.class))).thenReturn(response);

        ExoticBeastResponseDto result = exoticBeastServiceImpl.createExoticBeast(TEST_EXOTIC_BEAST_REQUEST);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Dustback Helamite");

        verify(unitRepository, times(1)).findById(1L);
        verify(exoticBeastRepository, times(1)).save(any(ExoticBeastEntity.class));
        verify(exoticBeastEventPublisher, times(1)).publishExoticBeastCreated(anyLong(),
                anyString());
    }

    @Test
    @DisplayName("Update exotic beast test method")
    void updateExoticBeastTest(){

        Long exoticBeastId = 1L;

        UnitEntity unitEntity = new UnitEntity();
        unitEntity.setId(1L);
        unitEntity.setName("Intercessor");

        ExoticBeastEntity existingExoticBeast = ExoticBeastEntity.builder()
                .id(1L)
                .name("Dustback Helamite")
                .build();

        ExoticBeastRequestDto updateRequest = ExoticBeastRequestDto.builder()
                .name("Helamite")
                .unitId(1L)
                .build();

        ExoticBeastEntity updateExoticBeast = ExoticBeastEntity.builder()
                .id(1L)
                .name("Helamite")
                .build();

        ExoticBeastResponseDto response = ExoticBeastResponseDto.builder()
                .id(1L)
                .name("Helamite")
                .build();

        when(unitRepository.findById(1L)).thenReturn(Optional.of(unitEntity));
        when(exoticBeastRepository.findById(exoticBeastId)).thenReturn(Optional.of(existingExoticBeast));
        when(exoticBeastRepository.save(any(ExoticBeastEntity.class))).thenReturn(updateExoticBeast);
        when(exoticBeastMapper.toDto(any(ExoticBeastEntity.class))).thenReturn(response);

        ExoticBeastResponseDto result = exoticBeastServiceImpl.updateExoticBeast(exoticBeastId, updateRequest);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(exoticBeastId);
        assertThat(result.getName()).isEqualTo("Helamite");

        verify(unitRepository, times(1)).findById(1L);
        verify(exoticBeastRepository, times(1)).save(any(ExoticBeastEntity.class));
        verify(exoticBeastEventPublisher, times(1)).publishExoticBeastUpdated(anyLong(),
                anyString());
    }

    @Test
    @DisplayName("Should delete existing exotic beast test")
    void deleteExoticBeastTest(){

        ExoticBeastEntity entity = ExoticBeastEntity.builder()
                .id(1L)
                .name("Dustback Helamite")
                .build();

        when(exoticBeastRepository.findById(1L)).thenReturn(Optional.of(entity));
        doNothing().when(exoticBeastRepository).delete(entity);
        doNothing().when(exoticBeastEventPublisher).publishExoticBeastDeleted(anyLong());

        exoticBeastServiceImpl.deleteExoticBeast(1L);

        verify(exoticBeastRepository, times(1)).findById(1L);
        verify(exoticBeastRepository, times(1)).delete(entity);
        verify(exoticBeastEventPublisher, times(1)).publishExoticBeastDeleted(1L);
    }

    @AfterEach
    void deleteDataFromDataBase(){
        System.out.println("After each: " + this);
    }

    @AfterAll
    static void closeConnectionPool(){
        System.out.println("After all: ");
    }
}
