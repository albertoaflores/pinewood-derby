<template>
    <div class="container-fluid racers-view">
        <div class="row">
            <h1>
                <font-awesome-icon :icon="['fas', 'users']" /> Racers
                <button type="button" v-b-modal.racerModal class="btn btn-sm btn-success">
                    <font-awesome-icon :icon="['fas', 'user-plus']" /> Add
                </button>
            </h1>
            
        </div>
        <div class="col contestants">

            <b-modal id="racerModal" size="sm" title="Racer Info" @ok="saveRacerInfo">

                <b-form>
                    <div class="racerModal-picture text-center">
                        <input type="file" id="racerPictureFile" style="display:none"/>
                        <img alt="Pinewood Derby" class="rounded img-thumbnail" src="../assets/default-racer.png">
                    </div>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="racerNameLabel">Name</span>
                        </div>
                        <b-form-input v-model="racerForm.name" required placeholder="Enter name"/>
                    </div>
                    
                    <b-form-checkbox
                        v-model="racerForm.enabled"
                        name="checkbox-1"
                        value=true
                        unchecked-value=false switch>
                        Enable Flag
                    </b-form-checkbox>

                </b-form>
            </b-modal>

            <div class="row">
                <RacerThumbnail  
                    v-for="currentRacer in this.racers" 
                    v-bind:key="currentRacer.uuid"
                    v-bind:racer="currentRacer"/>
            </div>
        </div>
        
    </div>

</template>

<script>
// <input type="checkbox" class="custom-control-input" id="racerEnableCheck">
// <label class="custom-control-label" for="racerEnableCheck">Enable Racer</label>
import RacerThumbnail from '@/components/RacerThumbnail.vue'
import axios from 'axios'

export default {
    name: 'racers',
    components: {
        RacerThumbnail
    },
    data() {
        return {
            racerForm: {
                name: '',
                enabled: false
            },
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
        },
        saveRacerInfo() {
            console.log('Saving Racer!');
            var racerFormMine = JSON.stringify(this.racerForm);
            
            console.log(racerFormMine);
            
            axios.post('/api/racer/', racerFormMine, {
                    headers: {
                        'Content-Type': 'application/json',
                    }
                })
                .catch(e => {
                        this.errors.push(e)
                    });

            this.retrieveRacers();
        }
    },
    created() {
        this.retrieveRacers();
    }
}
</script>
<style scoped>
.contestants {
    padding-left: 10px;
    padding-right: 10px;
}
.racers-view {
    padding-bottom: 40px;
}
.racerModal-picture {
    padding-bottom: 10px;
}
</style>