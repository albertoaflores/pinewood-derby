<template>
    <div class="col-4 text-center" >
        <div class="lane">Lane {{ laneNumber }}</div>
        
        <div class="racer-name current-racer"> {{ racerInfo.name }} </div>
        <div class="best-time"><font-awesome-icon :icon="['fas', 'clock']" /> Best Time: {{ racerInfo.bestTime }} sec</div>
        
        <div class="time" v-if="laneResult.time" ><font-awesome-icon :icon="['far', 'clock']" /> {{ laneResult.time }} sec</div>
        <div class="alert alert-success" v-if="laneResult.rank && laneResult.rank == 'FIRST_PLACE'">
            <font-awesome-icon :icon="['fas', 'trophy']" /> Winner! 
        </div>
    </div>
</template>

<script>
export default {
    name: 'LaneInfo',
    props: {
        laneNumber: {
            type: Number,
            required: true
        },
        racerInfo: {
            type: Object,
            default: function () {
                return {
                    name: 'Racer Name',   
                    bestTime: '10.000'
                }
            }
        },
        laneResult: {
            type: Object,
            default: function () {
                return {
                    time: '',
                    rank: ''
                }
            }
        }
    }
}
</script>

<style scoped>
.lane {
    font-size: 80%;
    color: black;
    font-weight: bold;
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
    border-radius: 15px;
}
.best-time {
    color: gray;
    font-size: 60%;
}
</style>