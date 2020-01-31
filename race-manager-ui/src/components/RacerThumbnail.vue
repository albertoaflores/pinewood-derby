<template>
    <div class="col-2 text-center racer-info" v-if="racer">

        <button type="button" class="close racer-delete-button" @click="deleteRacer" aria-label="Close">
            <font-awesome-icon :icon="['fas', 'minus-circle']" size="xs" /> 
        </button>
        <div class="racer-picture" @click="editRacer">
            <img alt="Pinewood Derby" class="img-thumbnail" 
                 src="../assets/default-racer.png" >
        </div>
        <div class="racer-name">{{ racer.name }}</div>
        <div class="racer-status">
            <div v-if="racer.enabled" class="badge racer-ready">
                Ready
            </div>
            <div v-else class="badge racer-notReady">
                Not Ready
            </div>
        </div>

        <b-modal :id="racer.uuid" size="sm" title="Edit Racer" @ok="saveRacerInfo">

            <b-form>
                
                <div class="racerModal-picture text-center">
                    <input type="file" id="racerPictureFile" style="display:none"/>
                    <img alt="Pinewood Derby" class="rounded img-thumbnail" src="../assets/default-racer.png">
                </div>

                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="racerNameLabel">Name</span>
                    </div>
                    <b-form-input v-model="racer.name" required placeholder="Enter name"/>
                </div>
                
                <b-form-checkbox
                    v-model="racer.enabled"
                    name="checkbox-1" switch>
                    Enable Flag
                </b-form-checkbox>

            </b-form>
        </b-modal>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'racerThumbnail',
    props: { 
        racer: {
            type: Object,
            default: function() {
                return {
                    name: '',
                    uuid: '',
                    enabled: false
                }
            }
        }
    },
    methods: {
        deleteRacer() {
            console.log('Deleting racer ' + this.racer.uuid)

            axios.delete('/api/racer/' + this.racer.uuid)
                .catch(e => {
                        this.errors.push(e)
                });
            this.$emit("fooEvent")
        },
        editRacer() {
            console.log('Editing ' + this.racer.uuid)
            this.$bvModal.show(this.racer.uuid)
        },
        saveRacerInfo() {
            console.log('Saving Racer!');
            var racerFormMine = JSON.stringify(this.racer);
            
            axios.put('/api/racer/' + this.racer.uuid, racerFormMine, {
                    headers: {
                        'Content-Type': 'application/json',
                    }
                })
                .catch(e => {
                        this.errors.push(e)
                    });
        }
    }
}
</script>
<style scoped>
.racer-info {
    padding-bottom: 25px;
}
.racer-picture {
    padding-bottom: 5px;
}
.racer-status {
    font-size: 80%;
}
.racer-ready {
    color: #ffffff;
    background-color: green;
}
.racer-notReady {
    color: #ffffff;
    background-color: red;
}
.racerModal-picture {
    padding-bottom: 10px;
}
.racer-delete-button {
    margin-bottom: -25px;
    margin-right: 5px;
    color: red;
}
</style>