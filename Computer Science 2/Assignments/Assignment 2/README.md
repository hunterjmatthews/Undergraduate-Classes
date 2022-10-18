# Problem:
### Part 1: BasicTime
1. Design a class representing BasicTime for representing the correct time of the day (Hours and Minutes and AM/PM). Your class should contain the following methods
  - add: for adding two BasicTime objects. Non static.
  - addTo: same thing as add, except it is a static method
  - subtract: for subtracting two BasicTimes. Non static.
  - subtractFrom: same as subtract, except it is a static method.
  - Provide three constructors:
    - BasicTime (String time)
    - BasicTime(int hour, int minute,boolean morning)
    - BasicTime (): creates a BasicTime with value representing midnight (12:00AM).
    - Both BasicTime(String time) and BasicTime() should call BasicTime(int hour, int minute,boolean morning) to store hour, minute and AM/PM values to its class attributes.
2. Write a test program that prompts the user to enter two times following the 12-hour time format.
Use regex to validate entry and use only String class methods to parse it into hours, minutes and
AM/PM field. Display the result of their addition and subtraction using both static and non-static
methods.

### Part 2: PreciseTime
1. Design a class representing PreciseTime that inherits from BasicTime. Since the formula for
adding and subtracting is a bit different, you will have to redefine add, addTo, subtract,
and subtractFrom
  - Provide three constructors similar to BasicTime: PreciseTime(String time), PreciseTime(int hour, int minute, int second, boolean
morning), and PreciseTime(). PreciseTime() creates a PreciseTime with
value representing midnight (12:00:00AM). Both PreciseTime(String time) and
PreciseTime() should call PreciseTime(int hour, int minute, int
second, boolean morning) to store hour, minute, second and AM/PM values to its
class attributes. Also, make sure that your PreciseTime(int hour, int
minute, int second boolean morning) calls explicitly the BasicTime
(int hour, int minute, boolean morning) constructor, then initializes the
second attribute separately.
  - Similar logic should be followed for calculating addition as BasicTime
2. Write a test program that prompts the user to enter two times following the detailed 12-hour time format
(time, minute, sec), then store them using two different PreciseTime reference objects. Use regex to
validate entry and use only String class methods to parse it into hours, minutes. seconds and AM/PM field.
Display the result of their addition and subtraction using both static and non-static methods.
