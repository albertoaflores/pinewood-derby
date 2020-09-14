<template>
    <div class="container-fluid">
        <div class="row">
            <h1>
                <font-awesome-icon :icon="['fas', 'road']" /> Heat Schedules
                <button type="button" v-b-modal.scheduleModal class="btn btn-sm btn-success">
                    <font-awesome-icon :icon="['fas', 'user-plus']" /> Add
                </button>
            </h1>
        </div>
        <div>
            <b-modal id="scheduleModal" size="med" title="New Heat Schedule" @ok="saveSchedule">
                <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text"># of Heats</span>
                        </div>
                        <b-form-input v-model="scheduleForm.numberOfHeatsPerRacer" required 
                            placeholder="# of Heats Per Racer"/>
                    </div>
            </b-modal>
        </div>
        <div>
            <EventScheduleSummary v-for="currentSchedule in this.heats" 
                            v-bind:key="currentSchedule.uuid"
                            v-bind:eventSchedule="currentSchedule"
                            />
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import EventScheduleSummary from '@/components/EventScheduleSummary.vue';

export default {
    name: 'heats',
    components: {
        EventScheduleSummary
    },
    data() {
        return {
            scheduleForm: {
                numberOfHeatsPerRacer: 0
            },
            heats: [],
            errors: []
        }
    },
    methods: {
        saveSchedule() {
            console.log('Adding new schedule')
            var scheduleFormPayload = JSON.stringify(this.scheduleForm);
            axios.post('/api/heat/schedule/', scheduleFormPayload, {
                    headers: {
                        'Content-Type': 'application/json',
                    }
                })
                .catch(e => {
                        this.errors.push(e)
                    });

            this.retrieveSchedules();
        },
        retrieveSchedules() {
            console.log('Retrieving schedules')
            axios.get('/api/heat/schedule/')
                .then(response => {
                    console.log(response.data)
                    this.heats = response.data;
                })
                .catch(e => {
                        this.errors.push(e)
                    })
        }
    },
    created() {
        this.retrieveSchedules();
    }
}
</script>