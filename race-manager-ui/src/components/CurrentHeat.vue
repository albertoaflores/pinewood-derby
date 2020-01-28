<template>
    <div v-if="mode == 'normal'">
        <h1>
            <font-awesome-icon :icon="['fas', 'road']" /> Current Heat
            <button type="button" @click="retrieveHeatResults()" class="btn btn-sm btn-success">
                <font-awesome-icon :icon="['fas', 'play-circle']" /> Run
            </button>
            
        </h1>
        <div class="row" >
            <LaneInfo :laneNumber=1 v-bind:laneResult="results.lane1" />
            <LaneInfo :laneNumber=2 v-bind:laneResult="results.lane2" />
            <LaneInfo :laneNumber=3 v-bind:laneResult="results.lane3" />
        </div>
    </div>
    <div v-else-if="mode == 'anonymous'">
        <h1>
            <font-awesome-icon :icon="['fas', 'road']" /> Heat Results
            <button type="button" :disabled='disabledState.stopButton' @click="stopPolling()" class="btn btn-sm btn-danger">
                <font-awesome-icon :icon="['fas', 'stop-circle']" /> Stop
            </button>
            &nbsp;
            <button type="button" :disabled='disabledState.watchButton' @click="startPolling()" class="btn btn-sm btn-success">
                <font-awesome-icon :icon="['fas', 'play-circle']" /> Watch
            </button>
        </h1>
        <div class="row">
            <AnonymousLaneInfo :laneNumber=1 v-bind:laneResult="results.lane1" />
            <AnonymousLaneInfo :laneNumber=2 v-bind:laneResult="results.lane2" />
            <AnonymousLaneInfo :laneNumber=3 v-bind:laneResult="results.lane3" />
        </div>
    </div>
    <div v-else>
        Unknown Mode
    </div>
</template>

<script>
import axios from 'axios'
import LaneInfo from '@/components/LaneInfo.vue'
import AnonymousLaneInfo from '@/components/AnonymousLaneInfo.vue'

export default {
    name: 'CurrentHeat',
    components: {
        LaneInfo, AnonymousLaneInfo
    },
    props: {
        mode: {
            type: String,
            required: true
        }
    },
    data() {
        return {            
            results: {
                lane1: {},
                lane2: {},
                lane3: {},
            }, 
            disabledState: {
                stopButton: false,
                watchButton: true
            },
            errors: [],
            pollingJob: null
        }
    },
    methods: {
        stopPolling() {
            console.log('Stoping Poller!')
            clearInterval(this.pollingJob)
            this.disabledState.stopButton = true;
            this.disabledState.watchButton = false;            
        },
        startPolling() {
            console.log('Registering Job')
            this.pollingJob = setInterval(function () {
                    this.retrieveHeatResults()
                }.bind(this), 1000);
            this.disabledState.stopButton = false;
            this.disabledState.watchButton = true;  
        },
        retrieveHeatResults() {
            axios.get('/api/heat-results/recent')
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.results.lane1 = response.data['lane1']
                    this.results.lane2 = response.data['lane2']
                    this.results.lane3 = response.data['lane3']
                    })
                .catch(e => {
                        this.errors.push(e)
                    })
        }
    },
    created() {
        this.startPolling()
    }, 
    beforeDestroy() {
        this.stopPolling()
  },
}

</script>
