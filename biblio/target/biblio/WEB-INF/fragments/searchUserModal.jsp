<div id="findUserModal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg find-modal">
    <div class="modal-content">
        <div class="form-group">
          <label for="input-search" class="col-form-label">Buscar usuario por:</label>
          <input 
          type="text" 
          class="form-control" 
          id="input-userSearch" 
          name="search" 
          value="" 
          onkeydown="inputUserSearchKeyDown(event)"
          onblur="inputUserSearchKeyDown(event)"
          placeholder="Ingresa el nombre a buscar y presiona Enter"
          />
        </div>          
        <div class="table-container">
          <table class="table table-striped">
              <thead>
                  <tr>
                      <th scope="col">Nombre</th>
                      <th scope="col">Apellidos</th>
                      <th scope="col">Email</th>
                  </tr>
              </thead>
              <tbody id="tb-users-table-search">  
                  
              </tbody>
          </table>
        </div>
    </div>
  </div>
</div>