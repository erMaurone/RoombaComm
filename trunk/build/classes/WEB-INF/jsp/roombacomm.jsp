

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>RoombaComm control</title>
</head>

<body>
<!-- Include Simple Slider JavaScript and CSS -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="resources/simple-slider.js"></script>
<link href="resources/simple-slider.css" rel="stylesheet" type="text/css" />


<div id="video-jwplayer_wrapper" style="position: relative; display: block; width:95%; height: 540px;">
    <object type="application/x-shockwave-flash" data="jwplayer/jwplayer.flash.swf" width="90%" height="90%" bgcolor="#000000"
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

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(document).on("click", "#cameraOn", function() {
        $.ajax({
            asynch: false,
            url: "/roomba/cameraOn",
            type: 'GET',
            data: 'cameraOn=cameraOn',
            success: function(data){
                $("#txtArea").val(data);    //only display when jws script ends
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<script>
    $(document).on("click", "#cameraOff", function() {
        $.ajax({
            asynch: false,
            url: "/roomba/cameraOff",
            type: 'GET',
            data: 'cameraOff=cameraOff',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<script>
    $(document).on("click", "#panTilt", function() {

        var pan = $("#panSlider").val();
        var tilt = $("#tiltSlider").val();

        $.ajax({
            asynch: false,
            url: "/roomba/panTilt",
            type: 'POST',
            data: {panSlider: pan, tiltSlider: tilt},
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ==============================  DIRECTIONS ===================================-->

<!-- ======================  FWD  ====================== -->
<script>
    $(document).on("click", "#forward", function() {
        var speed = $("#speedSlider").val();
        var forward=$("#forward").val();

        $.ajax({
            asynch: false,
            url: "/roomba/direction",
            type: 'POST',
            data: {speedSlider: speed, forward: forward},
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  STOP  ====================== -->
<script>
    $(document).on("click", "#stop", function() {

        var response="test";

        $.ajax({
            asynch: false,
            url: "/roomba/direction",
            type: 'POST',
            data: 'stop=stop',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  TURN LEFT  ====================== -->
<script>
    $(document).on("click", "#turnLeft", function() {

        var response="test";

        $.ajax({
            asynch: false,
            url: "/roomba/direction",
            type: 'POST',
            data: 'turnLeft=turnLeft',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  TURN RIGHT  ====================== -->
<script>
    $(document).on("click", "#turnRight", function() {

        var response="test";

        $.ajax({
            asynch: false,
            url: "/roomba/direction",
            type: 'POST',
            data: 'turnRight=turnRight',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  SPIN LEFT  ====================== -->
<script>
    $(document).on("click", "#spinLeft", function() {

        var response="test";

        $.ajax({
            asynch: false,
            url: "/roomba/direction",
            type: 'POST',
            data: 'spinLeft=spinLeft',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  SPIN RIGHT  ====================== -->
<script>
    $(document).on("click", "#spinRight", function() {

        var response="test";

        $.ajax({
            asynch: false,
            url: "/roomba/direction",
            type: 'POST',
            data: 'spinRight=spinRight',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  BACKWARD  ====================== -->
<script>
    $(document).on("click", "#backward", function() {

        var response="test";

        $.ajax({
            asynch: false,
            url: "/roomba/direction",
            type: 'POST',
            data: 'backward=backward',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ==============================  DIRECTIONS END ================================-->

<!-- ==============================  CONTROLS ===================================-->
<!-- ======================  POWER ON  ====================== -->
<!-- ======================  POWER OFF  ====================== -->
<!-- ======================  FULL  ====================== -->
<!-- ======================  WAKE UP  ====================== -->
<!-- ======================  SAFE  ====================== -->

<!-- ======================  SING  ====================== -->
<script>
    $(document).on("click", "#sing", function() {
        $.ajax({
            asynch: false,
            url: "/roomba/controls",
            type: 'POST',
            data: 'sing=sing',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  VACCUM ON  ====================== -->
<script>
    $(document).on("click", "#vacuumOn", function() {
        $.ajax({
            asynch: false,
            url: "/roomba/controls",
            type: 'POST',
            data: 'vacuumOn=vacuumOn',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  VACCUM OFF  ====================== -->
<script>
    $(document).on("click", "#vacuumOff", function() {
        $.ajax({
            asynch: false,
            url: "/roomba/controls",
            type: 'POST',
            data: 'vacuumOff=vacuumOff',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  SENSOR DATA  ====================== -->
<script>
    $(document).on("click", "#sensorData", function() {
        $.ajax({
            asynch: false,
            url: "/roomba/controls",
            type: 'POST',
            data: 'sensorData=sensorData',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  CHARGE DATA  ====================== -->
<script>
    $(document).on("click", "#chargeData", function() {
        $.ajax({
            asynch: false,
            url: "/roomba/controls",
            type: 'POST',
            data: 'chargeData=chargeData',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  RESET  ====================== -->
<script>
    $(document).on("click", "#reset", function() {
        $.ajax({
            asynch: false,
            url: "/roomba/controls",
            type: 'POST',
            data: 'reset=reset',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  MAX  ====================== -->
<script>
    $(document).on("click", "#max", function() {
        $.ajax({
            asynch: false,
            url: "/roomba/controls",
            type: 'POST',
            data: 'max=max',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  CLEAN  ====================== -->
<script>
    $(document).on("click", "#clean", function() {
        $.ajax({
            asynch: false,
            url: "/roomba/controls",
            type: 'POST',
            data: 'clean=clean',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  SPOT  ====================== -->
<script>
    $(document).on("click", "#spot", function() {
        $.ajax({
            asynch: false,
            url: "/roomba/controls",
            type: 'POST',
            data: 'spot=spot',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ======================  DOCK  ====================== -->
<script>
    $(document).on("click", "#dock", function() {
        $.ajax({
            asynch: false,
            url: "/roomba/controls",
            type: 'POST',
            data: 'dock=dock',
            success: function(data){
                $("#txtArea").val(data);
            },
            error: function(data) {
                alert('woops!'+ data); //or whatever
            }
        });
    });
</script>
<!-- ==============================  CONTROLS END ===================================-->

<table name="layout"  border="1" style="width:95%">

    <tr>
        <td>
                <table style="margin-top:5px; margin-left:5px;">
                    <tr>
                        <td>
                            <button id="cameraOn" name="cameraOn" value="cameraOn" > CAMERA ON </button>
                        </td>
                        <td>
                            <button id="cameraOff" name="cameraOff" value="cameraOff"> CAMERA OFF </button>
                        </td>
                        <td>
                            <button id="panTilt" name="panTilt" value="panTilt" > SET SERVOS </button>
                        </td>
                    </tr>
                </table>
                <table style="margin-top:5px; margin-left:5px;">
                     <tr>
                        <td>
                            <label> PAN </label>
                        </td>
                     </tr>
                     <tr>
                        <td>
                            <input id="panSlider" type="text" data-slider="true" data-slider-range="3,98" data-slider-step="1" data-slider-highlight="true">

                        </td>
                     </tr>
                    <tr>
                        <td>
                            <label> TILT </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input id="tiltSlider" type="text" data-slider="true" data-slider-range="3,98" data-slider-step="1" data-slider-highlight="true">
                        </td>
                    </tr>

                    <tr>

                    </tr>
                </table>
        </td>

        <td>
                <table style="margin-top:5px; margin-left:5px;">
                    <tr>
                        <td>
                            <button id="turnLeft" name="turnLeft" value="turnLeft">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_turnleft.png">
                            </button>
                        </td>
                        <td>
                            <button id="forward" name="forward" value="forward">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_forward.png">
                            </button>
                        </td>
                        <td>
                            <button id="turnRight" name="turnRight" value="turnRight">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_turnright.png">
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button id="spinLeft" name="spinLeft" value="spinLeft" >
                                <img src="classes/com/hackingroomba/roombacomm/images/but_spinleft.png">
                            </button>
                        </td>
                        <td>
                            <button id="stop" name="stop" value="stop">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_stop.png">
                            </button>
                        </td>
                        <td>
                            <button id="spinRight" name="spinRight" value="spinRight">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_spinright.png">
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button id="sing" name="sing" value="sing">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_transport_play.png">
                            </button>
                        </td>
                        <td>
                            <button id="backward" name="backward" value="backward">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_backward.png">
                            </button>
                        </td>
                        <td>
                            <button id="dock" name="dock" value="dock">
                                <img src="classes/com/hackingroomba/roombacomm/images/but_dirtOn.png">
                            </button>

                        </td>
                    </tr>
                    <tr> </tr>
                </table>
        </td>
        <td>
                <table style="margin-top:5px; margin-left:5px; margin-bottom:5px">
                    <tr>
                        <td>
                            <button id="chargeData" name="chargeData" value="chargeData" > CHARGE </button>
                        </td>
                    </tr><tr>
                        <td>
                            <button id="sensorData" name="sensorData" value="sensorData" > SENSORS </button>
                        </td>
                    </tr>
                </table>
                <table style="margin-top:5px; margin-left:5px; margin-bottom:5px">
                    <tr>
                        <td>
                            <button id="max" name="max" value="max"> MAX </button>
                        </td>
                     </tr><tr>
                        <td>
                            <button id="clean" name="clean" value="clean"> CLEAN </button>
                        </td>
                    </tr><tr>
                        <td>
                            <button id="spot" name="spot" value="spot"> SPOT </button>
                        </td>
                    </tr>
                </table>
                <table style="margin-top:5px; margin-left:5px;">
                    <tr>
                        <td>
                            <button id="vacuumOn" name="vacuumOn" value="vacuumOn" > VACUUM-ON </button>
                        </td>
                    </tr><tr>
                        <td>
                            <button id="vacuumOff" name="vacuumOff" value="vacuumOff"> VACUUM-OFF </button>
                        </td>
                    </tr>
                </table>
        </td>
        <td>
            <div id="outputArea"/>
                <textarea id="txtArea" readonly name="display" cols="60" rows="16" style="color:yellow;background-color:black;">
                </textarea>

        </td>
    </tr>

</table>

</body>
</html>