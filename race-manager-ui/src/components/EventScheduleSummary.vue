<template>
    <div>
        <div>{{ eventSchedule.uuid }}</div>
        <div>Created: {{ eventSchedule.created }}</div>
        <div>Last Updated: {{ eventSchedule.updated }} </div>
        <div>Total # of Heats: {{ eventSchedule.totalNumberOfHeats }}</div>
        <div>Pending Heats: {{ eventSchedule.pendingHeatsToRun }}</div>
    </div>
</template>
<script>
export default {
    name: 'eventScheduleSummary',
    props: {
        eventSchedule: {
            type: Object,
            default: function() {
                return {
                    uuid: '',
                    created: '',
                    updated: '',
                    totalNumberOfHeats: '',
                    pendingHeatsToRun: ''
                }
            }
        }
    }
}
</script>