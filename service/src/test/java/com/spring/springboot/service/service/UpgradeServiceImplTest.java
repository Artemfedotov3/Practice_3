package com.spring.springboot.service.service;

import com.spring.springboot.api.dto.UpgradeRequestDto;
import com.spring.springboot.api.dto.UpgradeResponseDto;
import com.spring.springboot.service.entity.UnitEntity;
import com.spring.springboot.service.entity.UpgradeEntity;
import com.spring.springboot.service.mapper.UpgradeMapper;
import com.spring.springboot.service.rabbit.UpgradeEventPublisher;
import com.spring.springboot.service.repository.UnitRepository;
import com.spring.springboot.service.repository.UpgradeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@Tag("upgrade")
@Tag("service")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.DisplayName.class)
@ExtendWith(MockitoExtension.class)
public class UpgradeServiceImplTest {

    private static final UpgradeRequestDto TEST_UPGRADE_REQUEST = new UpgradeRequestDto();

    @Mock
    private UpgradeRepository upgradeRepository;

    @Mock
    private UpgradeMapper upgradeMapper;

    @Mock
    private UpgradeEventPublisher upgradeEventPublisher;

    @Mock
    private UnitRepository unitRepository;

    @InjectMocks
    private UpgradeServiceImpl upgradeServiceImpl;

    @BeforeAll
    static void init(){
        System.out.println("Before all: ");
        TEST_UPGRADE_REQUEST.setUpgradeName("Strength or Toughness");
        TEST_UPGRADE_REQUEST.setDescription("Боец получает +1 к силе или +1 к тафне " +
                "и так же увеличивает стоимость бойца на цену апгрейда");
        TEST_UPGRADE_REQUEST.setCost(10);
        TEST_UPGRADE_REQUEST.setUnitId(1L);
    }

    @BeforeEach
    void prepare(){
        System.out.println("Before each: ");
    }

    @Test
    void upgradeEmptyIfNoUpgradeAdded(){

        System.out.println("Test upgradeEmptyIfNoUpgradeAdded " + this);
        var upgrade = upgradeServiceImpl.getAllUpgrades();
        Assertions.assertTrue(upgrade.isEmpty(), () -> "Upgrade list should be empty");
    }

    @Test
    @DisplayName("Create upgrade test")
    void createUpgradeTest(){

        UnitEntity unitEntity = new UnitEntity();
        unitEntity.setId(1L);
        unitEntity.setName("Intercessor");

        UpgradeEntity entity = UpgradeEntity.builder()
                .id(1L)
                .upgradeName("Strength or Toughness")
                .build();

        UpgradeResponseDto response = UpgradeResponseDto.builder()
                .id(1L)
                .upgradeName("Strength or Toughness")
                .build();

        when(unitRepository.findById(1L)).thenReturn(Optional.of(unitEntity));
        when(upgradeRepository.save(any(UpgradeEntity.class))).thenReturn(entity);
        when(upgradeMapper.toDto(any(UpgradeEntity.class))).thenReturn(response);

        UpgradeResponseDto result = upgradeServiceImpl.createUpgrade(TEST_UPGRADE_REQUEST);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUpgradeName()).isEqualTo("Strength or Toughness");

        verify(unitRepository, times(1)).findById(1L);
        verify(upgradeRepository, times(1)).save(any(UpgradeEntity.class));
        verify(upgradeEventPublisher, times(1)).publishUpgradeCreated(anyLong(),
                anyString());
    }

    @Test
    @DisplayName("Update upgrade test method")
    void updateUpgradeTest(){

        Long upgradeId = 1L;

        UnitEntity unitEntity = new UnitEntity();
        unitEntity.setId(1L);
        unitEntity.setName("Intercessor");

        UpgradeEntity existingUpgrade = UpgradeEntity.builder()
                .id(1L)
                .upgradeName("Strength or Toughness")
                .build();

        UpgradeRequestDto updateRequest = UpgradeRequestDto.builder()
                .upgradeName("Strength and Toughness")
                .unitId(1L)
                .build();

        UpgradeEntity updateUpgrade = UpgradeEntity.builder()
                .id(1L)
                .upgradeName("Strength and Toughness")
                .build();

        UpgradeResponseDto response = UpgradeResponseDto.builder()
                .id(1L)
                .upgradeName("Strength and Toughness")
                .build();

        when(unitRepository.findById(1L)).thenReturn(Optional.of(unitEntity));
        when(upgradeRepository.findById(upgradeId)).thenReturn(Optional.of(existingUpgrade));
        when(upgradeRepository.save(any(UpgradeEntity.class))).thenReturn(updateUpgrade);
        when(upgradeMapper.toDto(any(UpgradeEntity.class))).thenReturn(response);

        UpgradeResponseDto result = upgradeServiceImpl.updateUpgrade(upgradeId, updateRequest);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(upgradeId);
        assertThat(result.getUpgradeName()).isEqualTo("Strength and Toughness");

        verify(unitRepository, times(1)).findById(1L);
        verify(upgradeRepository, times(1)).save(any(UpgradeEntity.class));
        verify(upgradeEventPublisher, times(1)).publishUpgradeUpdated(anyLong(),
                anyString());
    }

    @Test
    @DisplayName("Should delete existing exotic beast test")
    void deleteExoticBeastTest(){

        UpgradeEntity entity = UpgradeEntity.builder()
                .id(1L)
                .upgradeName("Strength or Toughness")
                .build();

        when(upgradeRepository.findById(1L)).thenReturn(Optional.of(entity));
        doNothing().when(upgradeRepository).delete(entity);
        doNothing().when(upgradeEventPublisher).publishUpgradeDeleted(anyLong());

        upgradeServiceImpl.deleteUpgrade(1L);

        verify(upgradeRepository, times(1)).findById(1L);
        verify(upgradeRepository, times(1)).delete(entity);
        verify(upgradeEventPublisher, times(1)).publishUpgradeDeleted(1L);
    }

    @AfterEach
    void deleteDataFromDataBase(){
        System.out.println("After each " + this);
    }

    @AfterAll
    static void closeConnectionPool(){
        System.out.println("After all: ");
    }
}
