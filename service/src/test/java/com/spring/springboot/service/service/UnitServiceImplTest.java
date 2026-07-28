package com.spring.springboot.service.service;

import com.spring.springboot.api.dto.UnitRequestDto;
import com.spring.springboot.api.dto.UnitResponseDto;
import com.spring.springboot.service.entity.UnitEntity;
import com.spring.springboot.service.mapper.UnitMapper;
import com.spring.springboot.service.rabbit.UnitEventPublisher;
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

@Tag("unit")
@Tag("service")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.DisplayName.class)
@ExtendWith(MockitoExtension.class)
public class UnitServiceImplTest {

    private static final UnitRequestDto TEST_REQUEST = new UnitRequestDto();

    @Mock
    private UnitRepository unitRepository;

    @Mock
    private UnitMapper unitMapper;

    @Mock
    private UnitEventPublisher eventPublisher;

    @InjectMocks
    private UnitServiceImpl unitServiceImpl;

    UnitServiceImplTest(TestInfo testInfo){
        System.out.println();
    }

    @BeforeAll
    static void init(){
        System.out.println("Before all: ");
        TEST_REQUEST.setName("Intercessor");
        TEST_REQUEST.setType("Troops");
        TEST_REQUEST.setM("6");
        TEST_REQUEST.setWS("3+");
        TEST_REQUEST.setBS("3+");
        TEST_REQUEST.setS("4");
        TEST_REQUEST.setT("4");
        TEST_REQUEST.setW("2");
        TEST_REQUEST.setI("3+");
        TEST_REQUEST.setA("2");
        TEST_REQUEST.setLD("7+");
        TEST_REQUEST.setCL("7+");
        TEST_REQUEST.setWILL("6+");
        TEST_REQUEST.setINTEL("6+");
        TEST_REQUEST.setXP("0");
    }

    @BeforeEach
    void prepare(){
        System.out.println("Before each: ");
    }

    @Test
    void unitsEmptyIfNoUnitAdded() {

        System.out.println("Test unitEmptyIfNoUnitAdded " + this);
        var units = unitServiceImpl.getAllUnits();
        Assertions.assertTrue(units.isEmpty(), () -> "Unit list should be empty");
    }

    @Test
    @DisplayName("Create unit test")
    void createUnitTest() {

        UnitEntity entity = new UnitEntity();
        entity.setId(1L);
        entity.setName("Intercessor");
        entity.setType("Troops");

        UnitResponseDto response = new UnitResponseDto();
        response.setId(1L);
        response.setName("Intercessor");
        response.setType("Troops");

        when(unitRepository.save(any(UnitEntity.class))).thenReturn(entity);
        when(unitMapper.toDto(any(UnitEntity.class))).thenReturn(response);

        UnitResponseDto result = unitServiceImpl.createUnit(TEST_REQUEST);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Intercessor");
        assertThat(result.getType()).isEqualTo("Troops");

        verify(unitRepository, times(1)).save(any(UnitEntity.class));
        verify(eventPublisher, times(1)).publishUnitCreated(anyLong(), anyString(), anyString());
    }

    @Test
    @DisplayName("Update unit test")
    void updateUnitTest(){
        Long unitId = 1L;

        UnitEntity existingUnit = new UnitEntity();
        existingUnit.setId(unitId);
        existingUnit.setName("Intercessor");
        existingUnit.setType("Troops");
        existingUnit.setMove_characteristic("6");

        UnitRequestDto updateRequest = new UnitRequestDto();
        updateRequest.setName("Updated Intercessor");
        updateRequest.setType("Elite");
        updateRequest.setM("7");
        updateRequest.setWS("2+");

        UnitEntity updatedUnit = new UnitEntity();
        updatedUnit.setId(unitId);
        updatedUnit.setName("Updated Intercessor");
        updatedUnit.setType("Elite");
        updatedUnit.setMove_characteristic("7");

        UnitResponseDto response = new UnitResponseDto();
        response.setId(unitId);
        response.setName("Updated Intercessor");
        response.setType("Elite");

        when(unitRepository.findById(unitId)).thenReturn(Optional.of(existingUnit));
        when(unitRepository.save(any(UnitEntity.class))).thenReturn(updatedUnit);
        when(unitMapper.toDto(any(UnitEntity.class))).thenReturn(response);

        UnitResponseDto result = unitServiceImpl.updateUnit(unitId, updateRequest);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(unitId);
        assertThat(result.getName()).isEqualTo("Updated Intercessor");
        assertThat(result.getType()).isEqualTo("Elite");

        verify(unitRepository, times(1)).findById(unitId);
        verify(unitRepository, times(1)).save(any(UnitEntity.class));
        verify(eventPublisher, times(1)).publishUnitUpdated(anyLong(), anyString(), anyString());
    }

    @Test
    @DisplayName("Should delete existing unit test")
    void deleteUnitTest() {

        UnitEntity entity = new UnitEntity();
        entity.setId(1L);
        entity.setName("Intercessor");
        entity.setType("Troops");

        when(unitRepository.findById(1L)).thenReturn(Optional.of(entity));
        doNothing().when(unitRepository).delete(entity);
        doNothing().when(eventPublisher).publishUnitDeleted(anyLong());

        unitServiceImpl.deleteUnit(1L);

        verify(unitRepository, times(1)).findById(1L);
        verify(unitRepository, times(1)).delete(entity);
        verify(eventPublisher, times(1)).publishUnitDeleted(1L);
    }

    @AfterEach
    void deleteDataFromDatabase(){
        System.out.println("After each: " + this);
    }

    @AfterAll
    static void closeConnectionPool(){
        System.out.println("After all: ");
    }
}