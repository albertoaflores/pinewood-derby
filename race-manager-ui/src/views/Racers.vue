<template>
    <div>
        <h1>
            <font-awesome-icon :icon="['fas', 'users']" /> Racers
            <button type="button" class="btn btn-sm btn-success">
                <font-awesome-icon :icon="['fas', 'user-plus']" /> Add
            </button>
        </h1>
        <div v-for="currentRacer in this.racers" v-bind:key="currentRacer.uuid">
            <RacerThumbnail v-bind:racer="currentRacer"/>
            <hr />
        </div>
    </div>
</template>

<script>
import RacerThumbnail from '@/components/RacerThumbnail.vue'
import axios from 'axios'

export default {
    name: 'racers',
    components: {
        RacerThumbnail
    },
    data() {
        return {
            racers: [],
            errors: []
        }
    },
    methods: {
        retrieveRacers() {
            axios.get('/api/racer/')
                .then(response => {
                    console.log(response.data)
                    this.racers = response.data;
                })
                .catch(e => {
                        this.errors.push(e)
                    })
        }
    },
    created() {
        this.retrieveRacers();
    }
}
</script>