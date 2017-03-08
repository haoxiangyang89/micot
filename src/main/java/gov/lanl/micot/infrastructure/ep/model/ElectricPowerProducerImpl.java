package gov.lanl.micot.infrastructure.ep.model;

import gov.lanl.micot.infrastructure.model.ProducerImpl;
import gov.lanl.micot.util.math.MathUtils;

/**
 * Implementation of electric power producers
 * 
 * @author Russell Bent
 */
public abstract class ElectricPowerProducerImpl extends ProducerImpl implements ElectricPowerProducer {

  /**
   * Constructor
   */
  protected ElectricPowerProducerImpl() {
    super();   
  }
     
  @Override
  public Number getDesiredRealGeneration() {
    return getAttribute(DESIRED_REAL_GENERATION_KEY, Number.class);
  }

  @Override
  public void setDesiredRealGeneration(Number realGeneration) {
    setAttribute(DESIRED_REAL_GENERATION_KEY, realGeneration);
  }

  @Override
  public Number getActualRealGeneration() {
    return getAttribute(ACTUAL_REAL_GENERATION_KEY, Number.class);
  }

  @Override
  public void setActualRealGeneration(Number realGeneration) {
    setAttribute(ACTUAL_REAL_GENERATION_KEY, realGeneration);
  }

  @Override
  public Number getDesiredReactiveGeneration() {
    return getAttribute(DESIRED_REACTIVE_GENERATION_KEY, Number.class);
  }

  @Override
  public void setDesiredReactiveGeneration(Number reactiveGeneration) {
    setAttribute(DESIRED_REACTIVE_GENERATION_KEY, reactiveGeneration);
  }

  @Override
  public Number getActualReactiveGeneration() {
    return getAttribute(ACTUAL_REACTIVE_GENERATION_KEY, Number.class);
  }

  @Override
  public void setActualReactiveGeneration(Number reactiveGeneration) {
    setAttribute(ACTUAL_REACTIVE_GENERATION_KEY, reactiveGeneration);
  }

  @Override
  public double getDesiredReactiveMax() {
    return getAttribute(DESIRED_REACTIVE_MAX_KEY, Double.class);
  }

  @Override
  public void setDesiredReactiveMax(double reactiveMaxRatio) {
    setAttribute(DESIRED_REACTIVE_MAX_KEY, reactiveMaxRatio);
  }

  @Override
  public double getReactiveMin() {
    return getAttribute(REACTIVE_MIN_KEY, Double.class);
  }

  @Override
  public void setReactiveMin(double reactiveMinRatio) {
    setAttribute(REACTIVE_MIN_KEY, reactiveMinRatio);
  }

  @Override
  public double getDesiredRealGenerationMax() {
    return getAttribute(DESIRED_REAL_GENERATION_MAX_KEY, Double.class);
  }

  @Override
  public void setDesiredRealGenerationMax(double realGenerationMax) {
    setAttribute(DESIRED_REAL_GENERATION_MAX_KEY, realGenerationMax);
  }

  @Override
  public double getRealGenerationMin() {
    return getAttribute(REAL_GENERATION_MIN_KEY, Double.class);
  }

  @Override
  public void setRealGenerationMin(double realGenerationMin) {
    setAttribute(REAL_GENERATION_MIN_KEY, realGenerationMin);
  }

  @Override
  public Number getDesiredProduction() {
    return MathUtils.SIGNED_NORM(getDesiredRealGeneration(), getDesiredReactiveGeneration());    
  }

  @Override
  public void setDesiredProduction(Number consumption) {
    setDesiredRealGeneration(consumption.doubleValue());
    setDesiredReactiveGeneration(0.0);
  }

  @Override
  public Number getActualProduction() {
    return MathUtils.SIGNED_NORM(getActualRealGeneration(), getActualReactiveGeneration());    
  }

  @Override
  public void setActualProduction(Number consumption) {
    setActualRealGeneration(consumption.doubleValue());
    setActualReactiveGeneration(0.0);
  }

  @Override
  public Number getMinimumProduction() {
  	double value = Math.sqrt(getRealGenerationMin() * getRealGenerationMin() + getReactiveMin() * getReactiveMin()); 
  	if (getRealGenerationMin() < 0 && getRealGenerationMin() < getReactiveMin()) {
    	value = -value;
    }
    else if (getReactiveMin() < 0 && getRealGenerationMin() > getReactiveMin()) {
    	value = -value;
    }  	
    return value; 
  }

  @Override
  public void setMinimumProduction(Number generation) {
    setRealGenerationMin(generation.doubleValue());
    setReactiveMin(0.0);
  }

  @Override
  public Number getMaximumProduction() {
    double value =  Math.sqrt(getDesiredRealGenerationMax() * getDesiredRealGenerationMax() + getDesiredReactiveMax() * getDesiredReactiveMax());
    if (getDesiredRealGenerationMax() < 0 && getDesiredRealGenerationMax() < getDesiredReactiveMax()) {
    	value = -value;
    }
    else if (getDesiredReactiveMax() < 0 && getDesiredRealGenerationMax() > getDesiredReactiveMax()) {
    	value = -value;
    }    
    return value;
  }

  @Override
  public void setMaximumProduction(Number production) {
    setDesiredRealGenerationMax(production.doubleValue());
    setDesiredReactiveMax(0.0);    
  }

  /**
   * Get economic cost
   * @return
   */
  public double getEconomicCost() {
    return getAttribute(ECONOMIC_COST_KEY,Number.class).doubleValue();
  }

  /**
   * Set the economic cost
   * @param cost
   */
  public void setEconomicCost(double cost) {
    setAttribute(ECONOMIC_COST_KEY,cost);    
  }
}