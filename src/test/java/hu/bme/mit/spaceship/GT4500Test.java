package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore tMockPTS;
  private TorpedoStore tMockSTS;

  @Before
  public void init(){
    tMockPTS = mock(TorpedoStore.class);
    tMockSTS = mock(TorpedoStore.class);
    this.ship = new GT4500(tMockPTS, tMockSTS);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(tMockPTS.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(tMockPTS, times(1)).fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(tMockPTS.fire(1)).thenReturn(true);
    when(tMockSTS.fire(1)).thenReturn(true);
    when(tMockPTS.isEmpty()).thenReturn(false);
    when(tMockSTS.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    verify(tMockPTS, times(1)).fire(1);
    verify(tMockSTS, times(1)).fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_First_Empty(){
    // Arrange
    when(tMockPTS.isEmpty()).thenReturn(true);
    when(tMockSTS.isEmpty()).thenReturn(false);
    when(tMockSTS.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(tMockSTS, times(1)).fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_Second_Empty(){
    // Arrange
    when(tMockSTS.isEmpty()).thenReturn(true);
    when(tMockPTS.isEmpty()).thenReturn(false);
    when(tMockPTS.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(tMockPTS, times(1)).fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_All_Empty(){
    // Arrange
    when(tMockPTS.isEmpty()).thenReturn(true);
    when(tMockSTS.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_Toggle_Check(){
    // Arrange
    when(tMockPTS.fire(1)).thenReturn(true);
    when(tMockSTS.fire(1)).thenReturn(true);
    when(tMockPTS.isEmpty()).thenReturn(false);
    when(tMockSTS.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    verify(tMockPTS, times(1)).fire(1);
    result = result && ship.fireTorpedo(FiringMode.SINGLE);
    verify(tMockSTS, times(1)).fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_First_Empty(){
    // Arrange
    when(tMockPTS.isEmpty()).thenReturn(true);
    when(tMockSTS.isEmpty()).thenReturn(false);
    when(tMockSTS.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    verify(tMockPTS, times(0)).fire(1);
    verify(tMockSTS, times(1)).fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_Second_Empty(){
    // Arrange
    when(tMockPTS.isEmpty()).thenReturn(false);
    when(tMockSTS.isEmpty()).thenReturn(true);
    when(tMockPTS.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    verify(tMockPTS, times(1)).fire(1);
    verify(tMockSTS, times(0)).fire(1);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_All_Empty(){
    // Arrange
    when(tMockPTS.isEmpty()).thenReturn(true);
    when(tMockSTS.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    verify(tMockSTS, times(0)).fire(1);
    verify(tMockSTS, times(0)).fire(1);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireLaser(){

    boolean result = ship .fireLaser(FiringMode.SINGLE);
    // Assert
    assertEquals(false, result);
  }

  @Test
  public void constructor_default(){

    GT4500 ship = new GT4500();


  }

}
