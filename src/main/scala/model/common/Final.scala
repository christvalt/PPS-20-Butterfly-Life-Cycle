package model.common



/** This class  represent all the simulation  default  paramenter*/
object Final {

  val DEF_BLOB_VELOCITY = 50
  val DEF_BLOB_SLOW_VELOCITY: Int = DEF_BLOB_VELOCITY / 2
  val MIN_BLOB_VELOCITY: Int = DEF_BLOB_SLOW_VELOCITY



/**    consts for reduce an increase life */
  val STANDARD_LIFE_DECREASE = 1
  val STANDARD_LIFE_INCREASE = 1




/**Eating effect parameter*/

  var DEF_BLOB_RADIUS: Int = 333
  var MIN_BLOB_RADIUS: Int = 444
  val MIN_BLOB_FOV_RADIUS: Int = 222
  var INTERVAL: Int = 1
  val DEF_FOOD_ENERGY = 2
  val REDUCE_LIFE = 1
  val DEF_NECTARD_ENERGY = 222
  val DEF_SIMPLE_PLANT_ENERGY = 63
  val REDUCE_LIFE_Larva = 5
  val REDUCE_LIFE_Puppa = 4
  val NULL_ENERGY = 0




  /** world parameter*/
  val WORLD_WIDTH = 1280
  val WORLD_HEIGHT = 720
  val TEMPERATURE_AMPLITUDE = 1.0125f
  val DEF_PREDATOR_PLANT_WIDTH = 8
  val DEF_PREDATOR_PLANT_HEIGHT = 12
  val ITERATIONS_PER_DAY = 100
  val BUTTERFLY_RADIUS = 25
  val DEF_BLOB_FOW_RADIUS= 10
  val BUTTERFLY_VELOCITY = 50
  val BUTTERFLY_LIFE = 700


  /** simaulation ùobject parameter*/




  val DEF_BUTTERFLY_LIFE = 1250
  val DEF_EGG_LIFE = 50
  val DEF_PUPPA_LIFE = 300
  val DEF_LARVA_LIFE = 50
  val DEF_BLOB_FOV_RADIUS = 50
  val DEF_NEXT_DIRECTION = 0
  val DEF_DAY_FOR_HIBERNATION_EGGS=500


  /**Point parameter */


  val WORLD_WIDTH2 = 1200

  val WORLD_HEIGHT3 = 700
  val DEF_EQUILATERAL_ANGLE = 20



  /**Point parameter */

  val EGG_RADIUS_ADD = 0.003

/**helper EggTo larva*/

 val LIFE_ADD_EGG_TO_LARVA = 15
  val VELOCITY_ADD_EGG_TO_LARVA = 15
  val VELOCITY_ADD_LARVA_TO_PUPPA = 20

}
