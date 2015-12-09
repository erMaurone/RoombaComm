

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>RoombaComm control</title>
</head>
<header><h1> Roomba Robot </h1> </header>
<body>

<div id="video-jwplayer_wrapper" style="position: relative; display: block; width:95%; height: 540px;">
    <object type="application/x-shockwave-flash" data="jwplayer/jwplayer.flash.swf" width="100%" height="100%" bgcolor="#000000"
            id="video-jwplayer" name="video-jwplayer" tabindex="0">
        <param name="allowfullscreen" value="true">
        <param name="allowscriptaccess" value="always">
        <param name="seamlesstabbing" value="true">
        <param name="wmode" value="opaque">
    </object>
    <div id="video-jwplayer_aspect" style="display: none;"></div>
    <div id="video-jwplayer_jwpsrv" style="position: absolute; top: 0px; z-index: 10;"></div>
</div>

<script src="jwplayer/jwplayer.js"></script>

<script type="text/javascript">
    jwplayer('video-jwplayer').setup({
        flashplayer:"jwplayer/jwplayer.flash.swf"
        , file:"rtmp://" + window.location.hostname + "/flvplayback/flv:myStream.flv"
        , autoStart: true
        , rtmp:{
            bufferlength:0.1
        }
        , deliveryType: "streaming"
        , width: 960
        , height: 540
        , player: {
            modes: {
                linear: {
                    controls:{
                        stream:{
                            manage:false
                            , enabled: false
                        }
                    }
                }
            }
        }
        , shows: {
            streamTimer: {
                enabled: true
                , tickRate: 100
            }
        }
    });
</script>

<table name="layout"  border="1" style="width:95%">
    <tr>
        <td><h3> Camera and tilt/pan servos </h3></td>
        <td><h3> Roomba Robot Direction </h3></td>
        <td><h3> Roomba Robot Controls </h3></td>
    </tr>
    <tr>
        <td>
                <table style="margin-top:5px; margin-left:5px;">
                    <tr>
                        <td>
                            <form action="cameraOn" method="post" name="cameraOn" >
                                <button type="submit" name="cameraOn" value="cameraOn" > CAMERA ON </button>
                            </form>
                        </td>
                        <td>
                            <form action="cameraOff" method="post" name="cameraOff" >
                                <button type="submit" name="cameraOff" value="cameraOff"> CAMERA OFF </button>
                            </form>
                        </td>
                    </tr>
                </table>
            <form action="panTilt" method="post" name="panTilt">
                <table style="margin-top:5px; margin-left:5px;">
                     <tr>
                        <td>
                            <label> PAN </label>
                        </td>
                        <td>
                            <label> TILT </label>
                        </td>
                     </tr>
                    <tr>
                        <td>
                            <p class="note"> <span style="color:blue">${pan}</span>
                                <input id="panSlider" name="panSlider" value="${pan}"
                                       type="range" min="0" max="100" step=1 />
                            </p>
                        </td>
                        <td>
                            <p class="note"> <span style="color:blue">${tilt}</span>
                                <input id="tiltSlider" name="tiltSlider" value="${tilt}"
                                       type="range" min="0" max="100" step=1 />
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                           <label for="panSlider"> 0% - 100% </label>
                        </td>
                        <td>
                            <label for="tiltSlider"> 0% - 100% </label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="submit" name="panTiltSubmit" value="panTiltSubmit" > SET SERVOS </button>
                        </td>
                    </tr>
                </table>
            </form>
        </td>
        <td>
            <form action="direction" method="post" name="direction" >
                <table style="margin-top:5px; margin-left:5px;">
                    <tr>
                        <td>
                            <button type="submit" name="turnLeft" value="turnLeft">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_turnleft.png">
                            </button>
                        </td>
                        <td>
                            <button type="submit" name="forward" value="forward">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_forward.png">
                            </button>
                        </td>
                        <td>
                            <button type="submit" name="turnRight" value="turnRight">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_turnright.png">
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button type="submit" name="spinLeft" value="spinLeft" >
                                <img src="classes/com/hackingroomba/roombacomm/images/but_spinleft.png">
                            </button>
                        </td>
                        <td>
                            <button type="submit" name="stop" value="stop">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_stop.png">
                            </button>
                        </td>
                        <td>
                            <button type="submit" name="spinRight" value="spinRight">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_spinright.png">
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button type="submit" name="backward" value="backward">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_backward.png">
                            </button>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <p class="note">Speed = <span style="color:blue">${speed}</span>
                            <input id="speedSlider" name="speedSlider" value="${speed}"
                                   type="range" min="0" max="500" step=1 />
                        </p>
                    </tr>
                </table>
            </form>
        </td>
        <td>
            <form action="controls" method="post" name="power" >
                <table style="margin-top:5px; margin-left:5px;">
                    <tr>
                        <td>
                            <button type="submit" name="powerOn" value="powerOn" > POWER ON </button>
                        </td>
                        <td>
                            <button type="submit" name="powerOff" value="powerOff"> POWER OFF </button>
                        </td>
                    </tr>
                </table>
            </form>
            <form action="controls" method="post" name="vacuum" value="vacuum" >
                <table style="margin-top:5px; margin-left:5px;">
                    <tr>
                        <td>
                            <button type="submit" name="vacuumOn" value="vacuumOn" > VACUUM ON </button>
                        </td>
                        <td>
                            <button type="submit" name="vacuumOff" value="vacuumOff"> VACUUM OFF </button>
                        </td>
                    </tr>
                </table>
            </form>
            <form action="controls" method="post" name="data" >
                <table style="margin-top:5px; margin-left:5px;">
                    <tr>
                        <td>
                            <button type="submit" name="chargeData" value="chargeData" > CHARGE DATA </button>
                        </td>
                        <td>
                            <button type="submit" name="sensorData" value="sensorData" > SENSORS DATA </button>
                        </td>
                    </tr>
                </table>
            </form>
            <form action="controls" method="post" name="mode" >
                <table style="margin-top:5px; margin-left:5px; margin-bottom:5px">
                    <tr>
                        <td>
                            <button type="submit" name="wakeUp" value="wakeUp"> WAKEUP </button>
                        </td>
                        <td>
                            <button type="submit" name="reset" value="reset"> RESET </button>
                        </td>
                        <td>
                            <button type="submit" name="passive" value="passive"> PASSIVE </button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button type="submit" name="full" value="full"> FULL </button>
                        </td>
                        <td>
                            <button type="submit" name="safe" value="safe"> SAFE </button>
                        </td>
                        <td>
                            <button type="submit" name="max" value="max"> MAX </button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button type="submit" name="clean" value="clean"> CLEAN </button>
                        </td>
                        <td>
                            <button type="submit" name="spot" value="spot"> SPOT </button>
                        </td>
                        <td>
                            <button type="submit" name="dock" value="dock"> DOCK </button>
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="3">
            <textarea readonly name="display" cols="110" rows="20" style="color:yellow;background-color:black;">
                ${output}
            </textarea>
        </td>
    </tr>

</table>

</body>
</html>