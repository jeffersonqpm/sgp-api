package br.com.treina.recife.sg.api.projeto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<ProjetoDTO> listarProjetos() {
        List<Projeto> projetos = projetoRepository.findAll();

        List<ProjetoDTO> dtos = new ArrayList<>();

        for (Projeto projeto : projetos) {

            dtos.add(projeto.toDTO());

        }

        return dtos;

    }

}
