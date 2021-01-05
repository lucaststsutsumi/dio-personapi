package one.innovation.personapi.service;

import one.innovation.personapi.MessageResponseDTO;
import one.innovation.personapi.dto.request.PersonDTO;
import one.innovation.personapi.entity.Person;
import one.innovation.personapi.exception.PersonNotFounfException;
import one.innovation.personapi.mapper.PersonMapper;
import one.innovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private PersonMapper personMapper = PersonMapper.INSTANCE;

    // put the @Autowired annotation on the constructor facilitates when the developer has to do the tests
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        final var personToSave = personMapper.toModel(personDTO);
        final Person savedPerson = personRepository.save(personToSave);

        return MessageResponseDTO
                .builder()
                .message("created person with ID: " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll() {
        final var all = personRepository.findAll();
        return all.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFounfException {
        final Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFounfException(id));
        return personMapper.toDTO(person);
    }
}
