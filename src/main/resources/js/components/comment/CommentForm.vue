<template>
  <v-layout class="px-3">
    <v-text-field
        label="Add comment"
        placeholder="Написать комментарий"
        v-model="text"
        @keyup.enter="save"
    />
    <v-btn @click="save" title="Добавить">
      Add
    </v-btn>
  </v-layout>
</template>

<script>
import {mapActions} from 'vuex';

export default {
  name: 'CommentForm',
  props: ['messageId'],
  data() {
    return {
      text: ''
    }
  },
  methods: {
    ...mapActions(['addCommentAction']),
    async save() {

      await this.addCommentAction({
        text: this.text,
        message: {
          id: this.messageId
        }
      })

      this.text = '';
    }
  }
}
</script>

<style scoped>

</style>
