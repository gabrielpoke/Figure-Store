<template>
  <div class="container-fluid mt-4">
    <h1 class="h1">Categorias</h1>
    <b-alert :show="loading" variant="info">Carregando...</b-alert>
    <b-row>
      <b-col>
        <table class="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>&nbsp;</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="category in categories" :key="category.id">
              <td>{{ category.id }}</td>
              <td>{{ category.name }}</td>
              <td class="text-right">
                <a href="#" @click.prevent="fill(category)">Editar</a> -
                <a href="#" @click.prevent="remove(category.id)">Remover</a>
              </td>
            </tr>
          </tbody>
        </table>
      </b-col>
      <b-col lg="3">
        <b-card :title="(model.id ? 'Editar Categoria ID#' + model.id : 'Nova Categoria')">
          <form @submit.prevent="create">
            <b-form-group label="Nome">
              <b-form-input type="text" v-model="model.name"></b-form-input>
            </b-form-group>
            <div>
              <b-btn type="submit" variant="success">Salvar</b-btn>
            </div>
          </form>
        </b-card>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import service from '@/api/category/service'
export default {
  data () {
    return {
      loading: false,
      categories: [],
      model: {}
    }
  },
  async created () {
    this.refresh()
  },
  methods: {
    async refresh () {
      this.loading = true
      this.categories = await service.findAll()
      this.loading = false
    },
    async fill (category) {
      this.model = Object.assign({}, category)
    },
    async create () {
      if (this.model.id) {
        await service.update(this.model.id, this.model)
      } else {
        await service.save(this.model)
      }
      this.model = {}
      await this.refresh()
    },
    async remove (id) {
      if (confirm('Tem certeza que deseja excluir essa Categoria?')) {
        if (this.model.id === id) {
          this.model = {}
        }
        await service.delete(id)
        await this.refresh()
      }
    }
  }
}
</script>
