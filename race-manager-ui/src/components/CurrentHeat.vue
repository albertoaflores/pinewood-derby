<template>
    <div>
        <h1><font-awesome-icon :icon="['fas', 'road']" /> Current Heat</h1>
        <div class="row">
                <div class="col-4 text-center">
                    <div class="lane">Lane 1</div>
                    <div class="racer-name current-racer">Christian </div>
                    <div class="best-time"><font-awesome-icon :icon="['fas', 'clock']" /> Best Time: 3.456</div>
                    <div class="time"><font-awesome-icon :icon="['far', 'clock']" /> 3.456</div>
                    <div class="position"><span class="badge badge-pill">--</span></div>
                </div>
                <div class="col-4 text-center">
                    <div class="lane">Lane 2</div>
                    <div class="racer-name current-racer">Reed </div>
                    <div class="best-time"><font-awesome-icon :icon="['fas', 'clock']" /> Best Time: 3.456</div>
                    <div class="time"><font-awesome-icon :icon="['far', 'clock']" /> 2.422</div>
                    <div class="position"><span class="badge badge-pill ">--</span></div>
                </div>
                <div class="col-4 text-center">
                    <div class="lane">Lane 3</div>
                    <div class="racer-name current-racer">Jarom </div>
                    <div class="best-time"><font-awesome-icon :icon="['fas', 'clock']" /> Best Time: 3.456</div>
                    <div class="time"><font-awesome-icon :icon="['far', 'clock']" /> 2.543</div>
                    <div class="position"><span class="badge badge-pill ">--</span></div>
                </div>
            </div>
    </div>
</template>

<script>
export default {
    name: 'CurrentHeat'
}
</script>

<style scoped>
.lane {
    font-size: 80%;
    color: gray;
}
.racer-name {
    font-size: 120%;
    
}
.racer-name.current-racer {
    font-size: 140%;
    color: white;
    background-color: black;   
    border: 2px solid green;
    padding: 5px;
    border-radius: 20px;
}
.best-time {
    color: gray;
    font-size: 60%;
}
</style>