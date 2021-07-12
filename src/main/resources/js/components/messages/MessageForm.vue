<template>
    <v-layout>
        <v-text-field
                label="New message"
                placeholder="Write something"
                v-model="text"
                @keyup.enter="save"
        />
        <v-btn @click="save">
            Save
        </v-btn>
    </v-layout>
</template>

<script>
    import { mapActions } from 'vuex';

    export default {
        props: ['messageAttr'],
        data() {
            return {
                text: '',
                id: null, // fixed a bug with adding a new message by changing '' to null
            }
        },
        watch: {
            messageAttr(newValue, oldValue) {
                this.text = newValue.text;
                this.id = newValue.id;
            }
        },
        methods: {
            ...mapActions(['addMessageAction', 'updateMessageAction']),
            save() {
                const message = {
                    id: this.id,
                    text: this.text,
                };

                if (this.id) {
                    this.updateMessageAction(message);
                } else {
                    this.addMessageAction(message);
                }

                this.text = '';
                this.id = null; // fixed a bug with adding a new message by changing '' to null
            }
        }
    }
</script>

<style>

</style>
