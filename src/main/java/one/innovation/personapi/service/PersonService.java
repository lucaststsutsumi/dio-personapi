package one.innovation.personapi.service;

import lombok.AllArgsConstructor;
import one.innovation.personapi.dto.response.MessageResponseDTO;
import one.innovation.personapi.dto.request.PersonDTO;
import one.innovation.personapi.entity.Person;
import one.innovation.personapi.exception.PersonNotFounfException;
import one.innovation.personapi.mapper.PersonMapper;
import one.innovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;
    private PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        final var personToSave = personMapper.toModel(personDTO);
        final Person savedPerson = personRepository.save(personToSave);

        return createMethodResponse(savedPerson.getId(), "created person with ID: ");
    }

    public List<PersonDTO> listAll() {
        final var all = personRepository.findAll();
        return all.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFounfException {
        final Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void deleteById(Long id) throws PersonNotFounfException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFounfException {
        verifyIfExists(id);
        final var personToUpdate = personMapper.toModel(personDTO);
        final Person savedPerson = personRepository.save(personToUpdate);

        return createMethodResponse(savedPerson.getId(), "Updated person with ID: ");
    }

    private MessageResponseDTO createMethodResponse(Long id, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFounfException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFounfException(id));
    }
}
