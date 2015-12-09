

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<html>
<head>
    <title>RoombaComm control</title>
</head>
<header><h1> Roomba Robot </h1> </header>
<body>

  <table name="layout"  border="1" style="width:65%">
   <tr>
   <td>
    <form action="" name="direction" style="margin-top:30px; margin-left:20px;">
        <table>
            <tr>
                <td>
                    <button type="submit" name="turnLeft">
                        <img src="classes/com/hackingroomba/roombacomm/images/but_turnleft.png">
                    </button>
                </td>
                <td>
                    <button type="submit" name="forward">
                        <img src="classes/com/hackingroomba/roombacomm/images/but_forward.png">
                    </button>
                </td>
                <td>
                    <button type="submit" name="turnRight">
                        <img src="classes/com/hackingroomba/roombacomm/images/but_turnright.png">
                    </button>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit" name="spinLeft">
                        <img src="classes/com/hackingroomba/roombacomm/images/but_spinleft.png">
                    </button>
                </td>
                <td>
                    <button type="submit" name="stop">
                        <img src="classes/com/hackingroomba/roombacomm/images/but_stop.png">
                    </button>
                </td>
                <td>
                    <button type="submit" name="spinRight">
                        <img src="classes/com/hackingroomba/roombacomm/images/but_spinright.png">
                    </button>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="submit" name="backward">
                        <img src="classes/com/hackingroomba/roombacomm/images/but_backward.png">
                    </button>
                </td>
                <td></td>
            </tr>
            <tr>

                <p class="note">Speed = <span id="currentValue">${speed}</span>
                    <input id="speedSlider" name="speedSlider" value="speedSlider"
                           type="range" min="0" max="500" step=1 />
                </p>
            </tr>
        </table>
    </form>
    </td>

    <td>
    <br> Power controls<br>
    <form action name="power" style="margin-top:5px; margin-left:20px;">
        <table>
            <tr>
                <td>
                    <button type="submit" name="powerOn"> POWER ON </button>
                </td>
                <td>
                    <button type="submit" name="powerOff"> POWER OFF </button>
                </td>
            </tr>
        </table>
    </form>
    <br> Vacuum controls<br>
    <form action name="vacuum" style="margin-top:5px; margin-left:20px;">
        <table>
            <tr>
                <td>
                    <button type="submit" name="vacuumOn"> VACUUM ON </button>
                </td>
                <td>
                    <button type="submit" name="vacuumOff"> VACUUM OFF </button>
                </td>
            </tr>
        </table>
    </form>

    <br>Data retrival<br>
    <form action name="data" style="margin-top:5px; margin-left:20px;">
        <table>
            <tr>
                <td>
                    <button type="submit" name="chargeData"> CHARGE DATA </button>
                </td>
                <td>
                    <button type="submit" name="sensorData"> SENSORS DATA </button>
                </td>
            </tr>
        </table>
    </form>
   <br>Mode controls<br>
    <form action name="mode" style="margin-top:5px; margin-left:20px; margin-bottom:15px">

        <table>
            <tr>
                <td>
                    <button type="submit" name="wakeUp"> WAKEUP </button>
                </td>
                <td>
                    <button type="submit" name="reset"> RESET </button>
                </td>
                <td>
                    <button type="submit" name="passive"> PASSIVE </button>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit" name="full"> FULL </button>
                </td>
                <td>
                    <button type="submit" name="safe"> SAFE </button>
                </td>
                <td>
                    <button type="submit" name="max"> MAX </button>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit" name="clean"> CLEAN </button>
                </td>
                <td>
                    <button type="submit" name="spot"> SPOT </button>
                </td>
                <td>
                    <button type="submit" name="dock"> DOCK </button>
                </td>
            </tr>
        </table>
    </form>
   </td>
  </tr>
  <tr>
      <td colspan="2">
          <textarea readonly name="display" cols="110" rows="20">
              Data values:
              ${output}
          </textarea>
      </td>
  </tr>
  </table>

</body>
</html>