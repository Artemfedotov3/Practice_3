package com.spring.springboot.service.service;

import com.spring.springboot.api.dto.EquipmentRequestDto;
import com.spring.springboot.api.dto.EquipmentResponseDto;
import com.spring.springboot.service.entity.EquipmentEntity;
import com.spring.springboot.service.entity.UnitEntity;
import com.spring.springboot.service.mapper.EquipmentMapper;
import com.spring.springboot.service.rabbit.EquipmentEventPublisher;
import com.spring.springboot.service.repository.EquipmentRepository;
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

@Tag("equipment")
@Tag("service")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.DisplayName.class)
@ExtendWith(MockitoExtension.class)
public class EquipmentServiceImplTest {

    private static final EquipmentRequestDto TEST_EQUIPMENT_REQUEST = new EquipmentRequestDto();

    @Mock
    private EquipmentRepository equipmentRepository;

    @Mock
    private EquipmentMapper equipmentMapper;

    @Mock
    private EquipmentEventPublisher equipmentEventPublisher;

    @Mock
    private UnitRepository unitRepository;

    @InjectMocks
    private EquipmentServiceImpl equipmentServiceImpl;

    @BeforeAll
    static void init(){
        System.out.println("Before all: ");
        TEST_EQUIPMENT_REQUEST.setEquipment_name("Venom caster");
        TEST_EQUIPMENT_REQUEST.setType("Toxin");
        TEST_EQUIPMENT_REQUEST.setS("-");
        TEST_EQUIPMENT_REQUEST.setL("T");
        TEST_EQUIPMENT_REQUEST.setST(0);
        TEST_EQUIPMENT_REQUEST.setAp("-1");
        TEST_EQUIPMENT_REQUEST.setCost(160);
        TEST_EQUIPMENT_REQUEST.setDamage(0);
        TEST_EQUIPMENT_REQUEST.setRange("T");
        TEST_EQUIPMENT_REQUEST.setTraits("Scarce, Silent, Template, Toxin");
        TEST_EQUIPMENT_REQUEST.setUnitId(1L);
    }

    @BeforeEach
    void prepare(){
        System.out.println("Before each: ");
    }

    @Test
    void equipmentsEmptyIfNoEquipmentAdded(){

        System.out.println("Test equipmentsEmptyIfNoEquipmentAdded " + this);
        var equipments = equipmentServiceImpl.getAllEquipments();
        Assertions.assertTrue(equipments.isEmpty(), () -> "Equipment list should be empty");
    }

    @Test
    @DisplayName("Equipment create test")
    void createEquipmentTest() {

        UnitEntity unitEntity = new UnitEntity();
        unitEntity.setId(1L);
        unitEntity.setName("Intercessor");

        EquipmentEntity entity = new EquipmentEntity();
        entity.setId(1L);
        entity.setEquipmentName("Venom caster");
        entity.setType("Toxin");

        EquipmentRequestDto request = new EquipmentRequestDto();
        request.setEquipment_name("Venom caster");
        request.setType("Toxin");
        request.setUnitId(1L);

        EquipmentResponseDto response = new EquipmentResponseDto();
        response.setId(1L);
        response.setEquipment_name("Venom caster");
        response.setType("Toxin");

        when(unitRepository.findById(1L)).thenReturn(Optional.of(unitEntity));
        when(equipmentRepository.save(any(EquipmentEntity.class))).thenReturn(entity);
        when(equipmentMapper.toDto(any(EquipmentEntity.class))).thenReturn(response);

        EquipmentResponseDto result = equipmentServiceImpl.createEquipment(TEST_EQUIPMENT_REQUEST);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getEquipment_name()).isEqualTo("Venom caster");
        assertThat(result.getType()).isEqualTo("Toxin");

        verify(unitRepository, times(1)).findById(1L);
        verify(equipmentRepository, times(1)).save(any(EquipmentEntity.class));
        verify(equipmentEventPublisher, times(1)).publishEquipmentCreated(anyLong(), anyString(),
                anyString());
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
