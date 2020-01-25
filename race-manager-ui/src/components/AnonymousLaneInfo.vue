<template>
    <div class="col-4 text-center">
        <div class="lane-number">Lane {{ laneNumber }}</div>
        
        <div class="lane-result"> 
            <div v-if="laneResult.time">
              <font-awesome-icon :icon="['far', 'clock']" /> {{ laneResult.time }} sec
            </div>
            <div v-else>
                Ready!
            </div>
        </div>
        <br />
        <div class="alert alert-success" v-if="laneResult.rank && laneResult.rank == 'FIRST_PLACE'">
            <font-awesome-icon :icon="['fas', 'trophy']" /> Winner! 
        </div>
    </div>
</template>

<script>
export default {
    name: 'AnonymousLaneInfo',
    props: {
        laneNumber: {
            type: Number,
            required: true
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
.lane-number {
    font-size: 110%;
    color: black;
    font-weight: bold;
}
.lane-result {
    font-size: 140%;
    color: white;
    background-color: black;   
    border: 2px solid green;
    padding: 5px;
    border-radius: 15px;
}
</style>