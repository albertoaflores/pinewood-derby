<template>
    <div>
        <h1>
            <font-awesome-icon :icon="['fas', 'road']" /> Current Heat
            <button type="button" @click="retrieveHeatResults()" class="btn btn-sm btn-success">
                <font-awesome-icon :icon="['fas', 'play-circle']" /> Run
            </button>
        </h1>
        <div class="row" v-if="mode == 'normal'" >
            <LaneInfo :laneNumber=1 v-bind:laneResult="results.lane1" />
            <LaneInfo :laneNumber=2 v-bind:laneResult="results.lane2" />
            <LaneInfo :laneNumber=3 v-bind:laneResult="results.lane3" />
        </div>
        <div class="row" v-if="mode == 'anonymous'" >
            <AnonymousLaneInfo :laneNumber=1 v-bind:laneResult="results.lane1" />
            <AnonymousLaneInfo :laneNumber=2 v-bind:laneResult="results.lane2" />
            <AnonymousLaneInfo :laneNumber=3 v-bind:laneResult="results.lane3" />
        </div>
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
            errors: []
        }
    },
    methods: {
        
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
    }
}

</script>
