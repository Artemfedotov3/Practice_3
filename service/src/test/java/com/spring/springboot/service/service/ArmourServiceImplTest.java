package com.spring.springboot.service.service;

import com.spring.springboot.api.dto.ArmourRequestDto;
import com.spring.springboot.api.dto.ArmourResponseDto;
import com.spring.springboot.service.entity.ArmourEntity;
import com.spring.springboot.service.entity.UnitEntity;
import com.spring.springboot.service.mapper.ArmourMapper;
import com.spring.springboot.service.rabbit.ArmourEventPublisher;
import com.spring.springboot.service.repository.ArmourRepository;
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

@Tag("armor")
@Tag("service")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.DisplayName.class)
@ExtendWith(MockitoExtension.class)
public class ArmourServiceImplTest {

    private static final ArmourRequestDto TEST_ARMOR_REQUEST = new ArmourRequestDto();

    @Mock
    private ArmourRepository armourRepository;

    @Mock
    private ArmourMapper armourMapper;

    @Mock
    private ArmourEventPublisher armourEventPublisher;

    @Mock
    private UnitRepository unitRepository;

    @InjectMocks
    private ArmourServiceImpl armourServiceImpl;

    @BeforeAll
    static void init(){
        System.out.println("Before all: ");
        TEST_ARMOR_REQUEST.setArmourName("Mesh armour");
        TEST_ARMOR_REQUEST.setType("Light");
        TEST_ARMOR_REQUEST.setDescription("Эта броня даёт спас бросок 5+");
        TEST_ARMOR_REQUEST.setCost(15);
        TEST_ARMOR_REQUEST.setUnitId(1L);
    }

    @BeforeEach
    void prepare(){
        System.out.println("Before each: ");
    }

    @Test
    void armoursEmptyIfNoArmourAdded(){

        System.out.println("Test armoursEmptyIfNoArmourAdded " + this);
        var armors = armourServiceImpl.getAllArmours();
        Assertions.assertTrue(armors.isEmpty(), () -> "Armor list should be empty");
    }

    @Test
    @DisplayName("Armor create test")
    void createArmorTest(){

        UnitEntity unitEntity = new UnitEntity();
        unitEntity.setId(1L);
        unitEntity.setName("Intercessor");

        ArmourEntity entity = new ArmourEntity();
        entity.setId(1L);
        entity.setArmourName("Mesh armour");
        entity.setType("Light");

        ArmourRequestDto request = new ArmourRequestDto();
        request.setArmourName("Mesh armour");
        request.setType("Light");
        request.setUnitId(1L);

        ArmourResponseDto response = new ArmourResponseDto();
        response.setId(1L);
        response.setArmourName("Mesh armour");
        response.setType("Light");

        when(unitRepository.findById(1L)).thenReturn(Optional.of(unitEntity));
        when(armourRepository.save(any(ArmourEntity.class))).thenReturn(entity);
        when(armourMapper.toDto(any(ArmourEntity.class))).thenReturn(response);

        ArmourResponseDto result = armourServiceImpl.createArmour(TEST_ARMOR_REQUEST);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getArmourName()).isEqualTo("Mesh armour");
        assertThat(result.getType()).isEqualTo("Light");

        verify(unitRepository, times(1)).findById(1L);
        verify(armourRepository, times(1)).save(any(ArmourEntity.class));
        verify(armourEventPublisher, times(1)).publishArmourCreated(anyLong(), anyString(),
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
