<template>
    <v-card class="my-2">
        <v-card-text>
            <user-link
                :user="message.author"
                size="48"
            ></user-link>
            <div class="pt-3">
                {{ message.text }}
            </div>
        </v-card-text>
        <media v-if="message.link" :message="message"></media>
        <v-card-actions>
            <v-btn small text rounded value="Edit" @click="edit">Edit</v-btn>
            <v-btn small icon value="X" @click="del">
                <v-icon>{{ deleteIcon }}</v-icon>
            </v-btn>
        </v-card-actions>
        <comment-list
            :comments="message.comments"
            :message-id="message.id"
        ></comment-list>
    </v-card>
</template>

<script>
    import { mdiDelete } from '@mdi/js';
    import { mapActions } from 'vuex';
    import CommentList from '../comment/CommentList.vue';
    import Media from 'components/media/Media.vue';
    import UserLink from 'components/UserLink.vue';

    export default {
        props: ['message', 'editMessage'],
        components: { UserLink, CommentList, Media },
        data() {
            return {
                deleteIcon: mdiDelete
            }
        },
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message);
            },
            del() {
                this.removeMessageAction(this.message);
            }
        }
    }
</script>

<style>

</style>